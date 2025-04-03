package com.procergs.quarkus.infra.exception.mapper;

import com.procergs.quarkus.infra.base.ConstraintMessageResolver;
import com.procergs.quarkus.infra.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Slf4j
@Provider
public class ConstraintViolationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {


    @Inject
    ConstraintMessageResolver messageResolver;

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        String fullConstraintName = exception.getConstraintName();
        String constraintName = extractConstraintName(fullConstraintName);
        String message = messageResolver.getMessage(constraintName);

        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(message))
                .build();
    }

    private String extractConstraintName(String fullConstraintName) {
        if (fullConstraintName != null && fullConstraintName.contains(".")) {
            return fullConstraintName.substring(fullConstraintName.lastIndexOf('.') + 1);
        }
        return fullConstraintName;
    }
}
