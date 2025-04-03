package com.procergs.apm.resource.turmaClient;

import java.util.List;

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
public class TurmaResponse {

    private int total;
    private int page;
    private int pageSize;
    private int pageCount;
    private boolean ascending;
    private List<TurmaDto> data;

}
