package com.procergs.apm;

import java.util.Optional;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.security.OAuthFlow;
import org.eclipse.microprofile.openapi.models.security.SecurityScheme;

public class CustomOASFilter implements OASFilter {

    @Override
    public SecurityScheme filterSecurityScheme(final SecurityScheme securityScheme) {
        Config config = ConfigProvider.getConfig();
        String authorizationUrl = config.getValue("soe.authorization.url", String.class);
        String tokenUrl = config.getValue("soe.token.url", String.class);

        Optional.ofNullable(securityScheme.getFlows()).ifPresent((flow) -> {
            OAuthFlow implicit = flow.getAuthorizationCode();
            implicit.setAuthorizationUrl(authorizationUrl);
            implicit.setTokenUrl(tokenUrl);

        });

        return securityScheme;
    }
}
