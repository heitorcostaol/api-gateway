package com.procergs.quarkus.infra.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String id;
    private String message;
    private String field;
    private String detail;

    public ErrorResponse() {

    }

    public ErrorResponse(String message, String field) {
        super();
        this.message = message;
        this.field = field;
    }

    public ErrorResponse(String message, String field, String detail) {
        super();
        this.message = message;
        this.field = field;
        this.detail = detail;
    }

    public ErrorResponse(String message) {
        super();
        this.message = message;
    }

    public ErrorResponse(String id, String message, String field, String detail) {
        super();
        this.id = id;
        this.message = message;
        this.field = field;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ErroRESTED [id=" + id + ", message=" + message + ", field=" + field + ", detail="
                + detail + "]";
    }


}
