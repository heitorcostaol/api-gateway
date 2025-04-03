package com.procergs.quarkus.infra.base;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

@RequestScoped
@Unremovable
public class RequestContext {

    @Inject
    HttpServletRequest request;

    public String getClientIp() {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
