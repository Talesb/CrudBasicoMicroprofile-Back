package br.com.hammerteam.crudbasico.resource;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;

import br.com.hammerteam.crudbasico.services.UserService;

@Path("/protected")
@RequestScoped
public class ProtectedUserResource {

	@Inject
	private UserService userService;

	@Inject
	@Claim("sub")
	private ClaimValue<String> sub;

	@GET
	@Path("/users")
	@RolesAllowed("protected")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allUsers() {
		return Response.ok(userService.findAll()).build();
	}
}
