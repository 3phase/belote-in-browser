package org.phase.game.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.phase.game.entities.Player;
import org.phase.game.entities.Room;
import org.phase.game.entities.Team;
import org.phase.game.gamecontext.GameServices;

@Path("/task")
public class PublicRest {
	
	private final GameServices gameServices;
	
	@Inject
	public PublicRest(GameServices gameSrv) {
		this.gameServices = gameSrv;
		System.out.println("Jajajaj");
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
