package com.procergs.quarkus.infra.base.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class RequestPesqDto {
    private OrdemRequestDto ordenacao; //para usar quando eh a ordenacao por apenas um campo
    private List<OrdemRequestDto> listaOrdem; //para usar quando a ordenacao eh por mais de um campo
    private Integer pagina;
    private Integer tamanhoPagina;
}
