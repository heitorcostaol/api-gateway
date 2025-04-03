package com.procergs.quarkus.infra.soeauth;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface SoeTokenRestClient {
    
    @FormUrlEncoded
    @POST("connect/token")
    Call<Token> consultaCep(
        @Field("client_id") String client_id,
        @Field("client_secret") String client_secret,
        @Field("grant_type") String grant_type);
}
