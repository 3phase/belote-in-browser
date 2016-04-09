package org.phase.game.entities;

public class Card {

	private final String owner; // Players
	private final String type;  // Colors
	private final Character mark; // Card_trumps / Card_suit
	
	public Card(String owner_, String type_, Character cardName) {
		this.owner = owner_;
		this.type = type_;
		this.mark = cardName;
	}

	public String getOwner() {
		return owner;
	}

	public String getType() {
		return type;
	}

	public Character getMark() {
		return mark;
	}
	
}
