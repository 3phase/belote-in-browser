package org.phase.game.rest;

import javax.ws.rs.core.MediaType;

import org.phase.game.entities.Player;
import org.phase.game.entities.Room;

public class PublicRest {
	
	private final Room room;
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public Room createNewRoom(Room room, Player player) {
		this.room = room;
		Team team = new Team();
		team.new_player(player);
		room.add_team(team);
		return room;
	}
	
	@POST
	@Concumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public void addPlayerToTeam(Player player) {
		
	}
	
}
