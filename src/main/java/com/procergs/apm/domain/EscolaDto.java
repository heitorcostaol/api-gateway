package com.procergs.apm.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.procergs.apm.resource.alunoClient.AlunoDto;
import com.procergs.apm.resource.professorClient.ProfessorDto;
import com.procergs.apm.resource.turmaClient.TurmaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EscolaDto {
    public List<AlunoDto> alunos;
    public List<ProfessorDto> professor;
    public List<TurmaDto> turma;

}
