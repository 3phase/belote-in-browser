$(document).ready(function() {
	
	"use strict";
	
	var roomStatus = false;
	var roomId = -1;
	var userId = -1;
	var canvasDefault = $(".game-container").clone();
	
	function checkIfRoomDone() {
		console.log("Debuging");
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + Cookies.get("room-token") + "/is-available",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "text/plain");
			},
			success: function(result) {
				console.log("Result mothafucka " + result);
				if (result == 1) { 
					roomStatus = true;
					/*clearInterval(interval);*/
					$(".game-container").replaceWith(canvasDefault);
				}
			},
			error: function(xhr, status, error) {
				console.log("Problem while creating room. " + JSON.stringify(xhr) + "; " + status + "; " + error);
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
	
	console.log("ROOOOOOM ID " + roomId);
	console.log("DEBUG " + "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + roomId + "/is-available");
	
	if (roomStatus == false) {
		$(".game-container").html("");
		$(".game-container").html("Waiting for the required amount of players to gather...");
		checkIfRoomDone();
		/*var interval = setInterval(checkIfRoomDone, 5000);*/
	}
	
});