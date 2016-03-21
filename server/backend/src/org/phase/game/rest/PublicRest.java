package org.phase.game.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.phase.game.entities.Player;
import org.phase.game.entities.Room;
import org.phase.game.entities.Team;

@Path("/task")
public class PublicRest {
	
	public PublicRest() {
		System.out.println("Public Rest was initialized");
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Room createNewRoom(Room room, Player player) throws Exception {
		Team team = new Team();
		team.new_player(player);
		room.add_team(team);
		return room;
	}
	
	
	// TODO: addPlayerToTeam
	// TODO: createNewRoom	
	
}
