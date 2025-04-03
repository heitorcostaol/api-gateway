package com.procergs.quarkus.infra.rest.parser;

import com.procergs.quarkus.infra.exception.RestException;

@FunctionalInterface
public interface RestClientErrorParser {

  RestException parse(int statusCode, String json);

  default RestException catchException(Exception exception) {
    return null;
  }
}
