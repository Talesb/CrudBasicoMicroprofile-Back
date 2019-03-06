package br.com.hammerteam.crudbasico.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.hammerteam.crudbasico.services.UserService;

@Path("/user")
@Singleton
public class UserResource {

	@Inject
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response allUsers() {
		return Response.ok(userService.findAll()).build();
	}

}
