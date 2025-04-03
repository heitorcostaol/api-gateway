package com.procergs.quarkus.infra.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.ws.rs.core.Response.Status;

@NoArgsConstructor
@Getter
public class NotFoundException extends RestException {

    public NotFoundException(String message) {
        super(Status.NOT_FOUND.getStatusCode(), message);
    }
}
