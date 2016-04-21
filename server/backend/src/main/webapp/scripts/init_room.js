$(document).ready(function() {
	"use strict";

	function createRoom() {
		var actionField = $(".play-options");
		var roomId = -1;
		
		actionField.html("");
		
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/create",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "text/plain");
			},
			success: function(result) {
				roomId = result;
				actionField.append("Room with ID <strong>" + roomId + "</strong> was created. You can now <a href=\"game.html\">start playing</a>...");
				if (Cookies.get("room-token") != undefined) {
					Cookies.remove("room-token");
				}
				Cookies.set("room-token", roomId, {path: "game.html"});
			},
			error: function(xhr, status, error) {
				actionField.append("Problem while creating room. " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
		
	}
	
	function joinRoom() {
		
	}
	
	function getRooms() {
		
	}
	
	$(".playOpt.create-room").on("click", createRoom);
	$(".playOpt.join-room").on("click", joinRoom);
	$(".playOpt.see-available").on("click", getRooms);
	
});