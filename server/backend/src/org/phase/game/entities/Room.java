package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private long roomId;
	private int status = 0;
	private List<Team> teams = new ArrayList<Team>();
	private Game game;
	
	public Room(long room_id) {
		this.roomId = room_id;
		System.out.println("ROOM ID IS " + this.roomId);
	}
	
	public void add_team(Team team) throws Exception {
		if (teams.size() < 2) {
			teams.add(team);
		} else {
			throw new Exception("A limit of two teams is present.");
		}
	}
	
	public void remove_team(Team team) throws Exception {
		if (status == 0) {
			teams.remove(team);
		} else {
			throw new Exception("A team cannot be removed while the game is running.");
		}
	}
	
	public void addPlayer(Player player) {
		for (Team team : teams) {
			if (team.if_ready() != 1) {
				try {
					team.new_player(player);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void start_game() throws Exception {
		if (teams.size() == 2 && status == 0) {
			status = 1;
			game = new Game(0, 0, teams);
		}
		throw new Exception("A game cannot be restarted while playing or started "
				+ "until the required amount of players is not present.");
	}
	
	public void close_game() throws Exception {
		if (status == 1) {
			throw new Exception("A game cannot be closed until it's not finished.");
		}
		status = 0;
	}
	
	public int get_status() {
		return status;
	}
	
	public Game get_game() {
		return this.game;
	}
	
}
