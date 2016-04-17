package org.phase.game.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.phase.game.entities.Card;
import org.phase.game.entities.Room;
import org.phase.game.gamecontext.BeloteInBrowser;

@Path("/play")
@Singleton
public class PublicRest {
	
	private final BeloteInBrowser game;
	private Card card;
	
	@Inject
	public PublicRest(BeloteInBrowser game_) {
		this.game = game_;
		System.out.println("Public rest was initialized");
	}
	
	@GET
	@Path("/room/start_game")
	@Produces("text/plain")
	public Integer startGame() {
		// TODO: Gotta use sth token like for proving authenticity of request 
		// since it's perfectly possible to simulate room creation
		Room room = this.game.createNewRoom();
		Integer roomId = this.game.rooms.indexOf(room);
		return roomId;
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
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/room/{roomId}/add-card")
	public Card addCardToCommonDesk(@PathParam("roomId") Integer roomId, List<Card> cards) {
		System.out.println("--- Debug ");
//		Game game = this.game.getRoomGame(roomId);
		for (Card card : cards) {
			System.out.println(card.getMark());
			System.out.println(card.getOwner());
			System.out.println(card.getType());
		}
		return this.card;
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
