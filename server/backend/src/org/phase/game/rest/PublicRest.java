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
	
	@GET
	@Path("/room/all/available")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Long> getCountOfAllRooms() {
		List<Long> listOfIds = new ArrayList<Long>();
		for (Room room : this.beloteInBrowser.rooms) {
			if (room.get_status() == 0) {
				listOfIds.add(room.get_id());
			}
		}
		return listOfIds;
	}
		
	@PUT
	@Path("/room/{id}/add-player")
	@Consumes({MediaType.APPLICATION_JSON})
	public void addPlayerToRoom(@PathParam("id") Integer roomId, Player player) {
		try {
			this.beloteInBrowser.addPlayerToRoom(roomId, player);
			long teamId = this.beloteInBrowser.rooms.get(roomId).getPlayersTeam(player);
			int realTeamId = ((int) teamId) - 1;
			int teamSize = this.beloteInBrowser.rooms.get(roomId).getTeams(realTeamId).getPlayers().size();
			if (teamId == 1) {
				// North only since south is given when the room is created
				player.setPlayerPosition("north");
			} else if (teamId == 2) {
				// East and west
				if (teamSize == 1) {
					player.setPlayerPosition("east");
				} else { player.setPlayerPosition("west"); } 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PUT
	@Path("/room/{id}/add-player/id")
	@Consumes("text/plain")
	public void addPlayerToRoomById(@PathParam("id") Integer roomId, String userId) {
		// Only used when new room		
		long playerId = Long.parseLong(userId);
		System.out.println("PUT Request UID " + playerId);
		Room room = this.beloteInBrowser.rooms.get(roomId);
		this.beloteInBrowser.addPlayerToRoomById(room, playerId);
		room.getAllTeams().get(0).get_player((int) playerId).setPlayerPosition("south");
	}
	
	@GET
	@Path("/room/{id}/t/{tId}/get-players")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Player> getAllUsers(@PathParam("id") Integer rId, @PathParam("tId") Integer teamId) {
		System.out.println("Room id " + rId);
		System.out.println("Team id " + teamId);
		return this.beloteInBrowser.rooms.get(rId).getTeams(teamId).getPlayers();
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
	@Path("/room/{roomId}/player/{playerId}/get-team")
	@Produces("text/plain")
	public long getTeam(@PathParam("roomId") Integer roomId, 
			@PathParam("playerId") long playerId) {
		System.out.println("TEST AND DEBUG " + roomId);
		for (Team team : this.beloteInBrowser.rooms.get(roomId).getAllTeams()) {
			if (team.checkIfContains(playerId)) {
				return team.get_team_id();
			}
		}
		return -1;
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
	
	@GET
	@Path("/room/{id}/is-available")
	@Produces("text/plain")
	public Integer isRoomAvailable(@PathParam("id") Integer roomId) {
		return this.beloteInBrowser.rooms.get(roomId).get_status();
	}

	@GET
	@Path("/room/{roomId}/t/{teamId}/player/{userId}/get-cards")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Card> getUserCards(@PathParam("roomId") Integer roomId, 
			@PathParam("teamId") Integer teamId, @PathParam("userId") Integer userId) {
		return this.beloteInBrowser.rooms.get(roomId).getAllTeams().get(teamId).getPlayerById(userId).getCards();
	}
	
	@GET
	@Path("/room/{roomId}/t/{teamId}/player/{userId}/get-position")
	@Produces("text/plain")
	public String getPlayerPosition(@PathParam("roomId") Integer roomId,
			@PathParam("teamId") Integer teamId, @PathParam("userId") Integer userId) {
		return this.beloteInBrowser.rooms.get(roomId).getAllTeams().get(teamId).getPlayerById(userId).getPlayerPosition();
	}
	
	@POST
	@Path("/room/{roomId}/t/{teamId}/player/{userId}/play-card")
	@Produces("text/plain")
	public Integer playCard(@PathParam("roomId") Integer roomId, @PathParam("teamId") Integer teamId,
			@PathParam("userId") Integer userId, Card cardToRemove) {
		// TODO: Fix static UID incrementation since it's not suitable for many users 		
		this.beloteInBrowser.rooms.get(roomId).getAllTeams().get(teamId).getPlayerById(userId).removeCard(cardToRemove);
		this.beloteInBrowser.rooms.get(roomId).get_game().addCardToDesk(cardToRemove);
		return this.beloteInBrowser.rooms.get(roomId).get_game().cardsOnDesk.size();
//		return this.beloteInBrowser.rooms.get(roomId).get_game().incrementPlayerTurn(userId);
	}
	
	@GET
	@Path("/room/{roomId}/next-turn")
	@Produces("text/plain")
	public Integer getNextTurn(@PathParam("roomId") Integer roomId) {
		return this.beloteInBrowser.rooms.get(roomId).get_game().getPlayerTurn();
	}
	
	@GET
	@Path("/room/{roomId}/common-desk-size")
	@Produces("text/plain")
	public Integer getCommonDeskSize(@PathParam("roomId") Integer roomId) {
		return this.beloteInBrowser.rooms.get(roomId).get_game().cardsOnDesk.size();
	}
	
	@GET
	@Path("/room/{roomId}/common-desk")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Card> getCommonDeskContents(@PathParam("roomId") Integer roomId) {
		return this.beloteInBrowser.rooms.get(roomId).get_game().cardsOnDesk;
	}
	
}
