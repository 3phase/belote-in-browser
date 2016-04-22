package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private long playerId;
	private String playerNickname;
	private Team team;
	private List<Card> cards = new ArrayList<Card>();

	public Player() {
		
	}
	
	public long getPlayerId() {
		return playerId;
	}
	
	public void addCardsToPlayer(Card card) {
		this.cards.add(card);
	}
	
	public void setPlayerTeam(Team team) {
		this.team = team;
	}
	
	public Team getPlayerTeam() {
		return this.team;
	}
	
	public void setPlayerNickname(String nickname) {
		this.playerNickname = nickname;
	}
	
	public String getPlayerNickName() {
		return this.playerNickname;
	}
	
}
