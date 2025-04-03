package com.procergs.quarkus.infra.soeauth.interceptor;

import com.procergs.quarkus.infra.soeauth.CriarAccessTokenSoeService;
import java.io.IOException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@ApplicationScoped
public class SoeAuthRestInterceptor implements Interceptor {

  @Inject
  private CriarAccessTokenSoeService criarAccessTokenSoeService;

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();
    Request.Builder requestBuilder = original.newBuilder()
        .header("Authorization", criarAccessTokenSoeService.executa());
    Request request = requestBuilder.build();
    return chain.proceed(request);
  }

}
