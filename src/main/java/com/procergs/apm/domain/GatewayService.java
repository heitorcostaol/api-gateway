package com.procergs.apm.domain;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.procergs.apm.resource.alunoClient.AlunoClient;
import com.procergs.apm.resource.alunoClient.AlunoResponse;
import com.procergs.apm.resource.professorClient.ProfessorClient;
import com.procergs.apm.resource.professorClient.ProfessorResponse;
import com.procergs.apm.resource.turmaClient.TurmaClient;
import com.procergs.apm.resource.turmaClient.TurmaResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class GatewayService {

    @Inject
    @RestClient
    AlunoClient AlunoClient;

    @Inject
    @RestClient
    ProfessorClient professorClient;

    @Inject
    @RestClient
    TurmaClient turmaClient;

    

    public String getStatus() {
        // Simulate a status check
        return "Gateway is up and running!";
    }

    @Transactional
    public String criarEscola(EscolaDto escola) {
        log.info("Iniciando a criação da escola...");

        log.info("Criando aluno...");
        AlunoClient.criarAluno(escola.getAlunos().get(0));

        log.info("Criando professor...");
        professorClient.criarProfessor(escola.getProfessor().get(0));

        log.info("Criando turma...");
        turmaClient.criarTurma(escola.getTurma().get(0));

        return "Escola criada com sucesso!";
    }

    @Transactional
    public EscolaDto consultarEscola() {
        log.info("Buscando escola...");

        log.info("buscando alunos...");
        AlunoResponse alunos = AlunoClient.listarAlunos();

        log.info("Buscando professores...");
        ProfessorResponse professores = professorClient.listarProfessores();

        log.info("Buscando turmas...");
        TurmaResponse turmas = turmaClient.listarTurmas();

        var escola = new EscolaDto(alunos.getData(), professores.getData(), turmas.getData());
        return escola;
    }

}
