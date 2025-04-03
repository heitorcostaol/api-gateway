package com.procergs.quarkus.infra.base;

import io.quarkus.security.identity.SecurityIdentity;
import java.time.LocalDateTime;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@ApplicationScoped
public class AuditEntityListener {

    @PreUpdate
    private void preencheDadosAtualizacao(AuditableEntity<?> entity) {
        RequestContext requestContext = get(RequestContext.class);

        entity.setUsuarioAtualizacao(getIdUsuario());
        entity.setDataAtualizacao(LocalDateTime.now());
        entity.setIpAtualizacao(requestContext.getClientIp());
    }

    @PrePersist
    private void preencheDadosInclusao(AuditableEntity<?> entity) {
        RequestContext requestContext = get(RequestContext.class);

        entity.setUsuarioInclusao(getIdUsuario());
        entity.setDataInclusao(LocalDateTime.now());
        entity.setIpInclusao(requestContext.getClientIp());
    }

    private Long getIdUsuario() {
        SecurityIdentity identity = get(SecurityIdentity.class);
        var principal = identity.getPrincipal();

        try {
            return Long.valueOf(principal.getName());
        } catch (Exception e) {
            return null;
        }
    }

    protected <T> T get(Class<T> beanClass) {
        return CDI.current().select(beanClass).get();
    }
}
