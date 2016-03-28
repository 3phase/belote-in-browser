package org.phase.game.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.phase.game.entities.Card;
import org.phase.game.gamecontext.BeloteInBrowser;

@Path("/play")
@Singleton
public class PublicRest {
	
	private final BeloteInBrowser game;
	
	@Inject
	public PublicRest(BeloteInBrowser game_) {
		System.out.println("HAHAHAH");
		this.game = game_;
	}
	
	@PUT
	@Path("/room/create")
	@Consumes({MediaType.APPLICATION_JSON})
	public void createRoom() {
		this.game.createNewRoom();
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
	
	@PUT
	@Path("/room/{room_id}/t/{team_id}/player/{player_id}/add_cards")
	@Consumes({MediaType.APPLICATION_JSON})
	public void addCardsToPlayer(@PathParam("room_id") Integer room_id,
			@PathParam("team_id") Integer team_id,
			@PathParam("player_id") Integer player_id, List<Card> cards) {
		game.getRoomGame(room_id).getPlayerById(team_id, player_id);
	}
	
	// TODO: addPlayerToTeam
	// TODO: createNewRoom	
	
}
