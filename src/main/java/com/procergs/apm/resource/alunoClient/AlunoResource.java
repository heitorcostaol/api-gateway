package com.procergs.apm.resource.alunoClient;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/alunos")
public class AlunoResource {

    @Inject
    @RestClient
    AlunoClient alunoClient;

    @POST
    @Path("/criar")
    public void criarAluno(AlunoDto aluno) {
        alunoClient.criarAluno(aluno);
    }

    @GET
    public void listarAlunos() {
        alunoClient.listarAlunos();
    }

}
