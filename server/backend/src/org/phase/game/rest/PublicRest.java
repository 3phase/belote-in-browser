package org.phase.game.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.phase.game.entities.Card;

@Path("/play")
public class PublicRest {
	
	@PUT
	@Path("/room/create")
	@Consumes({MediaType.APPLICATION_JSON})
	public void createRoom() {
		
	}
	
	@PUT
	@Path("/room/{id}/add")
	@Consumes({MediaType.APPLICATION_JSON})
	public void addPlayerToRoom(@PathParam("id") Integer room_id) {
		
	}
	
	@PUT
	@Path("/room/{room_id}/evaluate_cards")
	@Consumes({MediaType.APPLICATION_JSON})
	public void addCardToCommonDesk(@PathParam("room_id") Integer room_id, 
			Integer player_id) {
		// Evaluate cards that are on the common table
		
	}
	
	@GET
	@Path("/room/{room_id}/check_if_done")
	@Produces("text/plain")
	public boolean check_if_game_done() {
		return true;
	}
	
	@GET
	@Path("/room/{id}/common-desk")
	@Produces("text/plain")
	public List<Card> test(@PathParam("id") Integer id) {
		List<Card> listOfCards = new ArrayList<Card>();
		
		return(listOfCards);
	}
	
	// TODO: addPlayerToTeam
	// TODO: createNewRoom	
	
}
