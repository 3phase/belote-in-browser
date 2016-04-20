package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private long announce;
	private int team_one_result;
	private int team_two_result;
	
	private Team announcing_team;
	private List<Team> teams = new ArrayList<Team>();
	private List<Card> cards = new ArrayList<Card>();
	public Table table = new Table();
	
	public Game() {
		createCards();
	}
	
	private void createCards() {
		String[] cardMarks = {"clubs", "diamonds", "hearts", "spades"};
		String[] cardTypes = {"7", "8", "9", "10", "J", "Q", "K", "A"};
		for (String mark : cardMarks) {
			for (String type : cardTypes) {
				Card card = new Card();
				card.setMark(mark);
				card.setType(type);
				cards.add(card);
			}
		}
	}
	
	public void getCards() {
		for (Card card : cards) {
			System.out.println(card.getMark() + " " + card.getType());
		}
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
	
	public static void main(String[] args) {
		Game game = new Game();
		game.getCards();
	}
	
}
