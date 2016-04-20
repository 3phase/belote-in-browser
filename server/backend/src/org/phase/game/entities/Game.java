package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private long announce;
	private int team_one_result;
	private int team_two_result;
	private Team announcing_team;
//	private Team[] teams;
	private List<Team> teams = new ArrayList<Team>();
	private List<Player> players;
	public Table table = new Table();
	
	public Game() {
		
	}
	
	public long get_announce() {
		return announce;
	}
	
	public void set_announce(long announce_, Team team) {
		announce = announce_;
		announcing_team = team;
	}
	
	public void updateTeams(List<Team> teams) {
		this.teams = teams;
	}
	
/*	public Player getPlayerById(Integer team_id, Integer player_id) {
		Team wanted_team = teams[team_id];
		return wanted_team.get_player(player_id);
	}
*/	
}
