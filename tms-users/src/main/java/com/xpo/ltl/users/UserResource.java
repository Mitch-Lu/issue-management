package com.xpo.ltl.users;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.xpo.ltl.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApplicationScoped
@Path("/users")
@Api(value = "users")
public class UserResource
{
    @DefaultValue("v1")
    @HeaderParam("X-apiversion")
    private String apiVersion;

    @Inject
    private UserService userService;

    @POST
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@ApiOperation(value = "Create user", response = User.class)
	@ApiResponses({
		@ApiResponse(code = 400, message = "Bad Request: Invalid input"),
		@ApiResponse(code = 201, message = "User Added")
	})
    public Response add(final User user, @Context final UriInfo uriInfo)
    {
    	userService.add(user);
    	return Response.ok(user).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get user by user ID", response = User.class)
    @ApiResponses({
    	@ApiResponse(code = 400, message = "Bad Request: Invalid input"),
    	@ApiResponse(code = 404, message = "User NOT FOUND")
    })
	public Response get(@PathParam("id") final String id)
	{
		final Optional<User> user = userService.get(id);
		if (user.isPresent()) {
			return Response.ok(user.get()).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

    @PUT
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
		final boolean updated = userService.update(updateReqst);

		return updated ? Response.ok(updateReqst).build() : Response.status(Response.Status.NOT_FOUND).build();
	}

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @ApiOperation(value = "Remove user by user ID")
    public Response remove(@PathParam("id") final String id)
    {
    	userService.remove(id);
        return Response.ok().build();
    }
}
