package org.phase.game.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.openjpa.json.JSONObject;
import org.phase.game.entities.Player;
import org.phase.game.entities.Room;
import org.phase.game.entities.Team;

@Path("/task")
public class PublicRest {
	
	public PublicRest() {
		System.out.println("Public Rest was initialized");
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Room createNewRoom(final MyJaxBean object) throws Exception {
		System.out.println("HAHAHAHAHA");
		Room room = new Room();
		Player player = object.param1;
		Team team = new Team();
		team.new_player(player);
		room.add_team(team);
		return room;
	}
	
	
	// TODO: addPlayerToTeam
	// TODO: createNewRoom	
	
}
