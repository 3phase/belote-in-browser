package org.phase.game.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.phase.game.gamecontext.GameServices;

import com.google.common.net.MediaType;

@Path("/task")
public class PublicRest {
	
	@GET
	@Path("/helloWorld")
	@Produces("text/plain")
	public String test() {
		return("Hello world");
	}
	
	// TODO: addPlayerToTeam
	// TODO: createNewRoom	
	
}
