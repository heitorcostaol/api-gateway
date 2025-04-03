package com.procergs.quarkus.infra.rest;

import com.procergs.quarkus.infra.exception.RestException;
import com.procergs.quarkus.infra.rest.parser.GenericErrorParser;
import com.procergs.quarkus.infra.rest.parser.RestClientErrorParser;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import okhttp3.ResponseBody;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.ws.rs.core.Response.Status;
import retrofit2.Call;
import retrofit2.Response;

public class RestExecutor {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestExecutor.class);
  private static final List<RestClientErrorParser> DEFAULT_PARSERS = new LinkedList<>();
  private static final RestExecutor INSTANCE;

  static {
    DEFAULT_PARSERS.add(new GenericErrorParser());
    INSTANCE = withParsers();
  }

  private final List<RestClientErrorParser> parsers = new LinkedList<>();

  private RestExecutor() {}

  public static RestExecutor withParsers(RestClientErrorParser... parsersAdicionais) {
    RestExecutor restExecutor = new RestExecutor();
    restExecutor.parsers.addAll(Arrays.asList(parsersAdicionais));
    restExecutor.parsers.addAll(DEFAULT_PARSERS);
    return restExecutor;
  }

  public static <T> T executeRestCall(Call<T> call) {
    return INSTANCE.execute(call);
  }

  private <T> T execute(Call<T> call) {
    return executeWithoutConversion(call).body();
  }

  public <T> Response<T> executeWithoutConversion(Call<T> call) {
    try {
      Response<T> response = call.execute();
      if (response.isSuccessful()) {
        return response;
      }

      String json = parseErrorBody(response.errorBody());
      throw handleErrorResponse(response.code(), json);
    } catch (RestException exception) {
      throw exception;
    } catch (IOException exception) {
      throw handleIOException(exception, call);
    } catch (Exception exception) {
      throw parseException(exception).orElseGet(() -> handleUnknownException(exception, call));
    }
  }

  private <T> RestException handleIOException(IOException exception, Call<T> call) {
    LOGGER.warn(
        "IOException ao chamar o serviço na URL {}: {}",
        call.request().url(),
        exception.getMessage(), exception);
    return new RestException(Status.INTERNAL_SERVER_ERROR.getStatusCode(), exception.getMessage());
  }

  private String parseErrorBody(ResponseBody errorBody) throws IOException {
    return IOUtils.toString(
        Optional.ofNullable(errorBody)
            .map(ResponseBody::charStream)
            .orElse(new StringReader("{}")));
  }

  private RestException handleErrorResponse(int code, String json) {
    return parsers.stream()
        .map(parser -> parser.parse(code, json))
        .filter(Objects::nonNull)
        .findFirst()
        .orElseGet(() -> new RestException(code, "Falha ao chamar o serviço"));
  }

  private RestException handleUnknownException(Exception exception, Call<?> call) {
    LOGGER.error(
        "Erro ao chamar o serviço na URL {}: {}",
        call.request().url(),
        exception.getMessage(),
        exception);
    return new RestException(Status.INTERNAL_SERVER_ERROR.getStatusCode(), exception.getMessage());
  }

  private Optional<RestException> parseException(Exception exception) {
    return parsers.stream()
        .map(parser -> parser.catchException(exception))
        .filter(Objects::nonNull)
        .findFirst();
  }
}
