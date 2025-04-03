package com.procergs.apm.resource.turmaClient;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/turma")
public class TurmaResource {

    @Inject
    @RestClient
    TurmaClient turmaClient;

    @POST
    @Path("/criar")
    public void criarTurma(TurmaDto turma) {
        turmaClient.criarTurma(turma);
    }

}
