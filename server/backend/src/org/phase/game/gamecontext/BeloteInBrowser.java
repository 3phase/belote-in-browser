package org.phase.game.gamecontext;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.phase.game.entities.Game;
import org.phase.game.entities.Player;
import org.phase.game.entities.Room;

@Singleton
public class BeloteInBrowser {
	
	public ArrayList<Room> rooms = new ArrayList<Room>();

	public Room createNewRoom() {
		Room room = new Room(rooms.size());
		rooms.add(room);
		return(room);
	}
	
	public void addPlayerToRoom(Room room, Player player) throws Exception {
		if (!rooms.contains(room)) {
			throw new IllegalArgumentException("No such room instance in the game.");
		}
		room.addPlayer(player);
	}
	
	public Game getRoomGame(Integer room_id) {
		return rooms.get(room_id).get_game();
	}
	
}
