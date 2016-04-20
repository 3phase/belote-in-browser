$(document).ready(function() {
	
	"use strict";
	
	var roomStatus = false;
	var roomId = -1;
	var userId = -1;
	
	
	function checkIfRoomDone() {
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + roomId + "/is-available",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "text/plain");
			},
			success: function(result) {
				if (result == 1) { 
					roomStatus = true;
					clearInterval(interval);
					$(".game-container").html("Dobar den Evropa");
				}
			},
			error: function(xhr, status, error) {
				actionField.append("Problem while creating room. " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
	}
	
	
	if (Cookies.get("user-token") === undefined || Cookies.get("room-token") === undefined) {
		$(".game-container").html("");
		$(".game-container").html("Fill your <a href=\"initial_room.html\">data</a> first.")
		return false;
	}
	
	roomId = Cookies.get("room-token");
	userId = Cookies.get("user-token");
	
	var counter = 0;
//	while (roomStatus == false) {
	// Waiting for fourth person to join
	
	if (roomStatus == false) {
		$(".game-container").html("");
		$(".game-container").html("Waiting for the required amount of players to gather...");
		var interval = setInterval(checkIfRoomDone, 5000);
	}
//	}
	
});