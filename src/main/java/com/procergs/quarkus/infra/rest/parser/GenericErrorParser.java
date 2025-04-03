package com.procergs.quarkus.infra.rest.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.procergs.quarkus.infra.exception.RestException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class GenericErrorParser implements RestClientErrorParser {

  @Override
  public RestException parse(int statusCode, String json) {
    try {
      var mapper = new ObjectMapper();
      var retorno = mapper.readValue(json, Map.class);
      var message =
          StringUtils.firstNonBlank(
              Objects.toString(retorno.get("resposta"), null),
              Objects.toString(retorno.get("message"), null),
              Objects.toString(retorno.get("service"), null));
      if (Objects.nonNull(message)) {
        return new RestException(statusCode, message);
      }
    } catch (IOException exception) {
      log.debug("Falha ao converter erro genérico", exception);
    }
    return null;
  }
}
