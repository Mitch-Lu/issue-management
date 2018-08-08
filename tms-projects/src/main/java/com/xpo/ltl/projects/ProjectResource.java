package com.xpo.ltl.projects;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.xpo.ltl.entity.Project;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApplicationScoped
@Path("/projects")
@Api(value = "projects")
public class ProjectResource
{
    @DefaultValue("v1")
    @HeaderParam("X-apiversion")
    private String apiVersion;

    @EJB
    private ProjectService projectService;

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
	@ApiOperation(value = "Create Project", response = Project.class)
	@ApiResponses({
		@ApiResponse(code = 400, message = "Bad Request: Invalid input"),
		@ApiResponse(code = 201, message = "Project Added")
	})
    public Response add(
    	@ApiParam(value = "Project Model", required = true) final Project project,
    	@Context final UriInfo uriInfo)
    {
    	projectService.add(project);
    	return Response.ok(project).build();
    }
}
