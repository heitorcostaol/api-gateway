package com.procergs.quarkus.infra.security;

import com.procergs.quarkus.infra.security.lru.CacheManager;
import io.quarkus.security.credential.TokenCredential;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal;
import io.smallrye.mutiny.Uni;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.core.Response;

/**
 * Busca as permissões do usuário no sistema e adiciona no contexto do usuário. Desta forma é
 * possível usar a anotação @RolesAllowed para verificar as permissões. Faz um cache para evitar
 * chamar o serviço de permissões do SOEWS a cada transação da aplicação.
 *
 * @author mauricio-wodarski
 *
 * @see "Implementação do LRU: https://www.baeldung.com/java-lru-cache"
 */

@ApplicationScoped
public class RolesAugmentor implements SecurityIdentityAugmentor {

    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "procergs.sistema")
    String sistema;

    @ConfigProperty(name = "procergs.soews.url")
    String soewsURL;

    @Inject
    CacheManager cacheManager;

    @Override
    @ActivateRequestContext
    public Uni<SecurityIdentity> augment(SecurityIdentity identity,
            AuthenticationRequestContext context) {
        LOGGER.fine("ID SOE: " + identity.getPrincipal().getName());
        return Uni.createFrom().item(build(identity));
    }

    private Supplier<SecurityIdentity> build(SecurityIdentity identity) {
        if (identity.isAnonymous()) {
            return () -> identity;
        } else {
            QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder(identity);
            builder.addRoles(getPermissoes(identity));
            return builder::build;
        }
    }

    @SuppressWarnings("unchecked")
    private Set<String> getPermissoes(SecurityIdentity identity) {
        TokenCredential credential = identity.getCredential(TokenCredential.class);
        DefaultJWTCallerPrincipal principal = (DefaultJWTCallerPrincipal) identity.getPrincipal();
        String tokenID = principal.getTokenID();

        LOGGER.fine("Token ID " + tokenID);
        Set<String> cachedPermissions = cacheManager.getPermissions(tokenID);

        if (cachedPermissions != null) {
            LOGGER.fine("Permissões encontradas no cache. Não será feita consulta no SOEWS");
            return cachedPermissions;
        }

        LOGGER.fine("Criando cache de permissões");
        Builder target = ClientBuilder.newClient()
                .target(this.soewsURL + "/permissoes/{codUsuario}/acoes/{sistema}")
                .resolveTemplate("codUsuario", principal.getName())
                .resolveTemplate("sistema", this.sistema).request()
                .header("Authorization", "Bearer " + credential.getToken());

        try (Response response = target.get()) {
            Set<String> permissoes = response.readEntity(Set.class);
            LOGGER.fine(
                    String.format("Foram encontradas %d permissões para o usuário %s no sistema %s",
                            permissoes.size(), principal.getName(), this.sistema));
            LOGGER.fine("Adicionando permissões no cache");
            cacheManager.putPermissions(tokenID, permissoes);
            return permissoes;
        }
    }
}
