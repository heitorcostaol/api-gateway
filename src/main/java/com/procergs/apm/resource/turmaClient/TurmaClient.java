package com.procergs.apm.resource.turmaClient;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.procergs.quarkus.infra.soeauth.interceptor.SoeAuthRestInterceptor;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/turmas")
@RegisterRestClient(configKey = "turma-api")
@RegisterProvider(SoeAuthRestInterceptor.class)
@Produces(MediaType.APPLICATION_JSON)
public interface TurmaClient {

    @POST
    public void criarTurma(TurmaDto turma);

    @GET
    TurmaResponse listarTurmas();

}
