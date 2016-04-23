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
import org.phase.game.entities.Game;
import org.phase.game.entities.Player;
import org.phase.game.entities.Room;
import org.phase.game.entities.Team;
import org.phase.game.gamecontext.BeloteInBrowser;

@Path("/play")
@Singleton
public class PublicRest {
	
	private final BeloteInBrowser beloteInBrowser;
	private Card card;
	
	@Inject
	public PublicRest(BeloteInBrowser game_) {
		this.beloteInBrowser = game_;
		System.out.println("Public rest was initialized");
	}
	
	@GET
	@Path("/room/create")
	@Produces("text/plain")
	public Integer createRoom() {
		Room room = this.beloteInBrowser.createNewRoom();
		Integer roomId = this.beloteInBrowser.rooms.indexOf(room);
		return roomId;
	}
	
	@GET
	@Path("/room/all")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Long> getAllAvailableRooms() {
		// TODO: Check if there are any free player positions in room		
		List<Long> listOfIds = new ArrayList<Long>();
		for (Room room : this.beloteInBrowser.rooms) {
			listOfIds.add(room.get_id());
		}
		return listOfIds;
	}
		
	@PUT
	@Path("/room/{id}/add-player")
	@Consumes("text/plain")
	public void addPlayerToRoom(@PathParam("id") Integer roomId, String userId) {
		long playerId = Long.parseLong(userId);
		System.out.println("PUT Request UID " + playerId);
		Room room = this.beloteInBrowser.rooms.get(roomId);
		this.beloteInBrowser.addPlayerToRoomById(room, playerId);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/room/{roomId}/add-card")
	public Card addCardToCommonDesk(@PathParam("roomId") Integer roomId, List<Card> cards) {
		System.out.println("--- Debug ");
		Game game = this.beloteInBrowser.getRoomGame(roomId);
		for (Card card : cards) {
			game.table.add_card_to_table(card);
			System.out.println(card.getMark());
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
		beloteInBrowser.getRoomGame(room_id).getPlayerById(team_id, player_id);
	}
	
	@POST
	@Path("/player/create")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces("text/plain")
	public long createPlayer(Player player) {
		long playerId = this.beloteInBrowser.allPlayers.size();
		player.setPlayerId(playerId);
		this.beloteInBrowser.addPlayerToList(player);
		return playerId;
	}
	
	// TODO: addPlayerToTeam	
	
}
