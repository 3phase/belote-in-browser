package org.phase.game.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.phase.game.entities.Player;
import org.phase.game.entities.Room;
import org.phase.game.entities.Team;

@Path("/task")
public class PublicRest {
	
//	private final Room room;
	
	public PublicRest() {
		// TODO Auto-generated constructor stub
		System.out.println("HAHAHAH");
	}
	
//	public PublicRest(Room room_) {
//		this.room = room_;
//	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Room createNewRoom(Room room, Player player) throws Exception {
		Team team = new Team();
		team.new_player(player);
		room.add_team(team);
		return room;
	}
	
	@GET
	@Path("/{haha}")
	public void testPrint() {
		System.out.println("Test success");
	}
	
	@POST
//	@Consumes({MediaType.APPLICATION_JSON})
//	@Produces({MediaType.APPLICATION_JSON})
	public void addPlayerToTeam(Player player) {
		
	}
	
}
