package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private long playerId;
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
	
}
