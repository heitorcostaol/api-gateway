package com.procergs.quarkus.infra.exception;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RestException extends RuntimeException {

    private ArrayList<RestException> exceptions = new ArrayList<>();
    private String id;
    private String field;
    private String detail;
    private int statusCode;

    public RestException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public RestException(int statusCode, String field, String message) {
        this(statusCode, message);
        this.field = field;
    }

    public RestException(int statusCode, String id, String field, String message, String detail) {
        this(statusCode, field, message);
        this.id = id;
        this.detail = detail;
    }


    public void addException(int statusCode, String message) {
        exceptions.add(new RestException(statusCode, message));
    }

    public void addException(int statusCode, String field, String message) {
        exceptions.add(new RestException(statusCode, field, message));
    }

    public void addException(int statusCode, String id, String field, String message,
            String detail) {
        exceptions.add(new RestException(statusCode, id, field, message, detail));
    }

    public void addException(RestException exception) {
        exceptions.add(exception);
    }
}
