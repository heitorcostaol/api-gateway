package com.procergs.quarkus.infra.soeauth;

import static com.procergs.quarkus.infra.rest.RestExecutor.executeRestCall;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CriarAccessTokenSoeService {

    private static final String BEARER = "Bearer";
    private static final String GRANT_TYPE = "client_credentials";

    @Inject
    private SoeTokenRestClient client;

    @ConfigProperty(name = "client.i5.id")
    String clientId;

    @ConfigProperty(name = "client.i5.secret")
    String clientSecret;


    public String executa() {
        return new StringBuilder() //
                .append(BEARER) //
                .append(StringUtils.SPACE) //
                .append(criarAccessToken()) //
                .toString();
    }

    public String criarAccessToken() {
        Token token =
                executeRestCall(client.consultaCep(clientId, clientSecret, GRANT_TYPE));
        return token.getAccess_token();
    }
}
