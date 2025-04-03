package com.procergs.quarkus.infra.exception.mapper;

import com.procergs.quarkus.infra.base.ConstraintMessageResolver;
import com.procergs.quarkus.infra.exception.ErrorResponse;
import com.procergs.quarkus.infra.exception.RestException;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Slf4j
@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {


    @Inject
    ConstraintMessageResolver messageResolver;

    @Override
    public Response toResponse(RestException exception) {

        log.warn(exception.getMessage(), exception);

        if (exception.getExceptions().isEmpty()) {
            var erro = new ErrorResponse(exception.getId(), exception.getMessage(),
                    exception.getField(), exception.getDetail());

            return Response.status(exception.getStatusCode()).entity(Arrays.asList(erro))
                    .type(MediaType.APPLICATION_JSON).build();
        }

        List<ErrorResponse> erros =
                exception.getExceptions().stream().map(exc -> new ErrorResponse(exc.getId(),
                        exc.getMessage(), exc.getField(), exc.getDetail())).toList();

        return Response.status(Response.Status.BAD_REQUEST).entity(erros)
                .type(MediaType.APPLICATION_JSON).build();
    }
}

