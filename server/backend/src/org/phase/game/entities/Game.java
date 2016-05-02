package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

	private long announce;
	private int team_one_result;
	private int team_two_result;
	
	private Team announcing_team;
	private List<Team> teams = new ArrayList<Team>();
	private List<Card> cards = new ArrayList<Card>();
	private Integer playerTurn = 0;
	public Table table = new Table();
	public List<Card> cardsOnDesk = new ArrayList<Card>();
	
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
	
	private void distributeCards() {
		Random rand = new Random();
		for (Team team : teams) {
			for (Player player : team.getPlayers()) {
				List<Card> cardsForPlayer = new ArrayList<Card>();
				for (int i = 0; i <= 7; i++) {
					int  n = rand.nextInt(cards.size());
					Card cardToAdd = cards.get(n);
					cardToAdd.setOwner(player.getPlayerId());
					cardsForPlayer.add(cardToAdd);
					cards.remove(n);
				}
				player.setCards(cardsForPlayer);
			}
		}
	}
	
	private void evaluateCards() {
		// Currently cards are perceived as all-trumps		
		System.out.println("Will evaluate");
		team_one_result = 12;
		team_two_result = 10;
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
	
	public void startGame() {
		distributeCards();
	}
	
	public List<Integer> getResult() {
		List<Integer> result = new ArrayList<Integer>();
		result.add(team_one_result);
		result.add(team_two_result);
		return result; 
	}
	
	public List<Card> getCardsOnDesk() {
		return cardsOnDesk;
	}
	
	public Integer getPlayerTurn() {
		return playerTurn;
	}
	
	public Integer showHowManyCardsOnDesk() {
		return cardsOnDesk.size();
	}
	
	public void addCardToDesk(Card card) {
		if (cardsOnDesk.size() <= 4) {
			cardsOnDesk.add(card);
		} else {
			evaluateCards();
			cardsOnDesk.clear();
		}
	}
	
	public Integer incrementPlayerTurn(Integer playerId) {
		// TODO: Make it increment within users in current game	
		playerTurn = playerId;
		if (playerTurn == 3) {
			playerTurn = 0;
		}
		playerTurn++;
		return playerTurn;
	}
	
	
/*	public Player getPlayerById(Integer team_id, Integer player_id) {
		Team wanted_team = teams[team_id];
		return wanted_team.get_player(player_id);
	}
*/	
}
