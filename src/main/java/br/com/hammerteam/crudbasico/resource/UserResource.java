package br.com.hammerteam.crudbasico.resource;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;

import br.com.hammerteam.crudbasico.business.dto.UserDTO;
import br.com.hammerteam.crudbasico.business.dto.UserLoginDTO;
import br.com.hammerteam.crudbasico.secure.JWTUtils;
import br.com.hammerteam.crudbasico.services.UserService;
import io.undertow.util.Headers;

@Path("/user")
@RequestScoped
public class UserResource {

	@Inject
	private UserService userService;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserLoginDTO userDTO, @Context HttpServletResponse resp) {
		try {
			if (userService.login(userDTO)) {
				UserDTO user = userService.getUserByEmail(userDTO.getEmail());
				String jwt = JWTUtils.generateJWT(user);
				resp.addHeader(Headers.AUTHORIZATION_STRING, jwt);
				return Response.ok().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return Response.noContent().build();

		}
		return Response.noContent().build();
	}

}
