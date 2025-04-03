package com.procergs.quarkus.infra.base.dto;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class OrdemRequestDto {
    private String nomeCampo;
    
    @Default
    private boolean ascendente = true;        
}
