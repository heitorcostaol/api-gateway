package com.procergs.quarkus.infra.exception.mapper;

import com.procergs.quarkus.infra.exception.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Slf4j
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {

        String id = exception.getClass().getName();
        String detail = exception.toString();
        String message = "Ocorreu um erro inesperado ao processar esta requisição.";
        log.warn(message, exception);
        List<ErrorResponse> erros = new ArrayList<>();
        erros.add(new ErrorResponse(id, message, null, detail));

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erros)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
