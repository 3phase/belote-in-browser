package org.phase.game.gamecontext;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.phase.game.entities.Game;
import org.phase.game.entities.Player;
import org.phase.game.entities.Room;

public class BeloteInBrowser {
	
	public ArrayList<Room> rooms = new ArrayList<Room>();
	public ArrayList<Player> allPlayers = new ArrayList<Player>();
	
	public Room createNewRoom() {
		Room room = new Room(rooms.size());
		rooms.add(room);
		return(room);
	}
	
	public void addPlayerToList(Player player) {
		allPlayers.add(player);
	}
	
	public void addPlayerToRoom(Integer roomId, Player player) throws Exception {
		if (rooms.get(roomId) == null) {
			throw new IllegalArgumentException("No such room instance in the game.");
		}
		Room room = this.rooms.get(roomId);
		room.addPlayer(player);
	}
	
	public void addPlayerToRoomById(Room room, long playerId) {
		if (!rooms.contains(room)) {
			throw new IllegalArgumentException("No such room instance in the game.");
		}
		Player playerById = null;
		for (Player player : allPlayers) {
			if (player.getPlayerId() == playerId) {
				playerById = player;
			}
		}
		if (playerById == null) {
			throw new IllegalArgumentException("No such player in the game.");
		}
		room.addPlayer(playerById);
		
	}
	
	public Game getRoomGame(Integer room_id) {
		return rooms.get(room_id).get_game();
	}
	
}
