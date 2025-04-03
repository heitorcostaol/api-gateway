package com.procergs.apm.resource.professorClient;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/professor")
public class ProfessorResource {

    @Inject
    @RestClient
    ProfessorClient professorClient;

    @POST
    @Path("/criar")
    public void criarProfessor(ProfessorDto professor) {
        professorClient.criarProfessor(professor);
    }

}
