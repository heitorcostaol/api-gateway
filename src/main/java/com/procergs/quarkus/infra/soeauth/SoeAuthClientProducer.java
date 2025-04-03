package com.procergs.quarkus.infra.soeauth;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.inject.Produces;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Getter
public class SoeAuthClientProducer {

    public static final long serialVersionUID = 310085012504047716L;

    @ConfigProperty(name = "procergs.soeauth.url")
    private String baseUrl;

    @Produces
    public SoeTokenRestClient createSoeTokenRestClient() {
        return getRetrofitInstance().create(SoeTokenRestClient.class);
    }

    Retrofit getRetrofitInstance() {
        OkHttpClient.Builder httpClient = createClientBuilder();
        httpClient.readTimeout(60, TimeUnit.SECONDS);

        return createRetrofitBuilder().baseUrl(getBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create()).client(httpClient.build())
                .build();
    }

    OkHttpClient.Builder createClientBuilder() {
        return new OkHttpClient.Builder();
    }

    Retrofit.Builder createRetrofitBuilder() {
        return new Retrofit.Builder();
    }
}
