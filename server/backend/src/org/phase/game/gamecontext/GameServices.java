package org.phase.game.gamecontext;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.phase.game.entities.Player;
import org.phase.game.entities.Room;

@Singleton
public class GameServices {
	
	private ArrayList<Room> rooms = new ArrayList<Room>();

	@Inject
	public GameServices() {
		// TODO Auto-generated constructor stub
	}
	
	public Room createNewRoom() {
		Room room = new Room();
		rooms.add(room);
		return room;
	}
	
	public void addPlayerToRoom(Room room, Player player) throws Exception {
		if (!rooms.contains(room)) {
			throw new IllegalArgumentException("No such room instance in the game.");
		}
		room.addPlayer(player);
	}
	
}
