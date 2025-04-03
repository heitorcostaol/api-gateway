package com.procergs.apm.resource.alunoClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlunoDto {

    private String nomeAlu;

    private String campoTeste0;

    //private String campoTeste1;

    //private String campoTeste2;

    private Long versao;

}
