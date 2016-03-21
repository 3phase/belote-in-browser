package org.phase.game.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.phase.game.entities.Player;
import org.phase.game.entities.Room;

@XmlRootElement
class MyJaxBean {
	
	@XmlElement public Player param1;
	@XmlElement public Room param2;
	
	public MyJaxBean() {}
	
}