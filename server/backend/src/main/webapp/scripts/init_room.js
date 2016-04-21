$(document).ready(function() {
	"use strict";

	function createRoom() {
		var actionField = $(".play-options");
		
		
		actionField.html("");
		actionField.append("Room number X was created. You can now <a href=\"game.html\">start playing</a>...");
	}
	
	function joinRoom() {
		
	}
	
	function getRooms() {
		
	}
	
	$(".playOpt.create-room").on("click", createRoom);
	$(".playOpt.join-room").on("click", joinRoom);
	$(".playOpt.see-available").on("click", getRooms);
	
});