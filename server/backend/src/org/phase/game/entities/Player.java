package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
	
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	
	public String getPlayerNickname() {
		return playerNickname;
	}
	
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

}
