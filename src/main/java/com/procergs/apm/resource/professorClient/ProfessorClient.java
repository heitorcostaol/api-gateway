package com.procergs.apm.resource.professorClient;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.procergs.quarkus.infra.soeauth.interceptor.SoeAuthRestInterceptor;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/professores")
@RegisterRestClient(configKey = "professor-api")
@RegisterProvider(SoeAuthRestInterceptor.class)
@Produces(MediaType.APPLICATION_JSON)
public interface ProfessorClient {

    @POST
    public void criarProfessor(ProfessorDto professor);

    @GET
    ProfessorResponse listarProfessores();

}
