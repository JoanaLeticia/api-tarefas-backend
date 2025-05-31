package com.exemplo.tarefa;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {
    @Inject
    TarefaService service;

    private static final Logger LOG = Logger.getLogger(TarefaResource.class);

    @POST
    @Transactional
    public Response insert(@Valid TarefaDTO dto) {
        LOG.debug("Iniciando inserção de tarefas.");
        try {
            TarefaResponseDTO retorno = service.insert(dto);
            LOG.info("Tarefa inserido com sucesso.");
            return Response.status(Status.CREATED).entity(retorno).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.warn("Violação de restrição ao inserir Tarefa.");
            return Response.status(Status.BAD_REQUEST).entity(result).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid TarefaDTO dto, @PathParam("id") Long id) {
        try {
            service.update(dto, id);
            LOG.info("Tarefa atualizado com sucesso.");
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.warn("Violação de restrição ao atualizar Tarefa.");
            return Response.status(Status.BAD_REQUEST).entity(result).build();
        } catch (EntityNotFoundException e) {
            LOG.warn("Tarefa não encontrado para atualização.");
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.delete(id);
            LOG.info("Tarefa deletado com sucesso.");
            return Response.noContent().build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            LOG.warn("Violação de restrição ao deletar Tarefa.");
            return Response.status(Status.BAD_REQUEST).entity(result).build();
        } catch (EntityNotFoundException e) {
            LOG.warn("Tarefa não encontrado para exclusão.");
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response findAll() {
        LOG.info("Buscando todas as Tarefas.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        try {
            TarefaResponseDTO tarefa = service.findById(id);
            LOG.info("Tarefa encontrada por ID.");
            return Response.ok(tarefa).build();
        } catch (EntityNotFoundException e) {
            LOG.warn("Tarefa não encontrada por ID.");
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
