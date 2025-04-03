package com.procergs.quarkus.infra.base;

import com.procergs.quarkus.infra.message.Bundle;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConstraintMessageResolver {

    public String getMessage(String constraintName) {
        return Bundle.get(constraintName);
    }
}
