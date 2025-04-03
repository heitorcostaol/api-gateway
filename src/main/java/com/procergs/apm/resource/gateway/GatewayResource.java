package com.procergs.apm.resource.gateway;

import com.procergs.apm.domain.EscolaDto;
import com.procergs.apm.domain.GatewayService;
import com.procergs.apm.resource.alunoClient.AlunoDto;
import com.procergs.apm.resource.professorClient.ProfessorDto;
import com.procergs.apm.resource.turmaClient.TurmaDto;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/gateway")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GatewayResource {

    @Inject
    private GatewayService gatewayService;

    @GET
    @Path("/status")
    public Response getStatus() {
        return Response.ok(gatewayService.getStatus()).build();
    }

    @POST
    @Path("/criarEscola")
    public Response criarEscola(EscolaDto escola) {
        return Response.ok(gatewayService.criarEscola(escola)).build();
    }

    @GET
    @Path("/listarEscolas")
    public Response listarEscolas() {
        return Response.ok(gatewayService.consultarEscola()).build();
    }

}
