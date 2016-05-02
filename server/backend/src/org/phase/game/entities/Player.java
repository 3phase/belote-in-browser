package org.phase.game.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Player {

	private long playerId;
	private String playerNickname;
	private long team;
	private List<Card> cards = new ArrayList<Card>();
	private String playerPosition;
	
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
	
	public long getTeam() {
		return team;
	}
	
	public void setTeam(long teamId) {
		this.team = teamId;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}
	
	public String getPlayerPosition() {
		return this.playerPosition;
	}

}
