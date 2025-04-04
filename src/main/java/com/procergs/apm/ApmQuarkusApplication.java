package com.procergs.apm;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;


@OpenAPIDefinition(info = @Info(title = "APM Quarkus", version = "v1", description = "Aplicação Modelo Quarkus"),
				security = @SecurityRequirement(name = "SOEAUTH")
)
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "SOEAUTH",
                type = SecuritySchemeType.OAUTH2,
                in = SecuritySchemeIn.HEADER,
                flows = @OAuthFlows(
                        authorizationCode = @OAuthFlow( )
                )
        )
})
public class ApmQuarkusApplication extends Application  {
}
