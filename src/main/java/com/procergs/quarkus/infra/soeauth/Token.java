package com.procergs.quarkus.infra.soeauth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String access_token;
    private String token_type;
    private Long expires_in;
}
