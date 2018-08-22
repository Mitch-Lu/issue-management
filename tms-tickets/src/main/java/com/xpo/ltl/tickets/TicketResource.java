package com.xpo.ltl.tickets;

import java.util.Optional;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.xpo.ltl.entity.Ticket;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApplicationScoped
@Path("/tickets")
@Api(value = "tickets")
public class TicketResource
{
    @DefaultValue("v1")
    @HeaderParam("X-apiversion")
    private String apiVersion;

    @EJB
    private TicketService ticketService;

    @GET
    @Path("/health")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@ApiOperation(value = "API Health Check")
	public Response checkHealth()
	{
		return Response.ok().build();
	}

    @POST
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@ApiOperation(value = "Create Ticket", response = Ticket.class)
	@ApiResponses({
		@ApiResponse(code = 400, message = "Bad Request: Invalid input"),
		@ApiResponse(code = 201, message = "Ticket Added")
	})
    public Response add(
    	@ApiParam(value = "Ticket Model", required = true) final Ticket ticket,
    	@Context final UriInfo uriInfo)
    {
    	ticketService.add(ticket);
    	return Response.ok(ticket).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@ApiOperation(value = "List All Ticket Info", response = Ticket.class, responseContainer="List")
	public Response listAll()
	{
    	return Response.ok(ticketService.listAll()).build();
	}

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get Ticket Info by Ticket ID", response = Ticket.class)
    @ApiResponses({
    	@ApiResponse(code = 400, message = "Bad Request: Invalid input"),
    	@ApiResponse(code = 404, message = "Ticket NOT FOUND")
    })
	public Response get(@PathParam("id") final long id)
	{
		final Optional<Ticket> ticket = ticketService.get(id);
		if (ticket.isPresent()) {
			return Response.ok(ticket.get()).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

/*    @PUT
    @Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@ApiOperation(value = "Update user", response = User.class)
	@ApiResponses({
		@ApiResponse(code = 400, message = "Bad Request: Invalid input"),
		@ApiResponse(code = 404, message = "User NOT FOUND"),
		@ApiResponse(code = 200, message = "User Updated")
	})
    public Response update(@PathParam("id") final String id, final User updateReqst)
	{
		updateReqst.setUserId(id);
		final boolean updated = ticketService.update(updateReqst);

		return updated ? Response.ok(updateReqst).build() : Response.status(Response.Status.NOT_FOUND).build();
	}

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @ApiOperation(value = "Remove user by user ID")
    public Response remove(@PathParam("id") final String id)
    {
    	ticketService.remove(id);
        return Response.ok().build();
    }*/
}
