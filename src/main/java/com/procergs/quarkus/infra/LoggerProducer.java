package com.procergs.quarkus.infra;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

public final class LoggerProducer {

    @Produces
    public Logger createLogger(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }

}
