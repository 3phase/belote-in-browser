$(document).ready(function() {
	"use strict";
	
	function setLoggedUserCookie(userId) {
		if (Cookies.get("user-token") != undefined) {
			Cookies.remove("user-token");
		}
		Cookies.set("user-token", userId, {path: "/"});
	}
	
	function createPlayer() {
		var playerId = $(".id").val();
		var playerNickname = $(".nickname").val();
		
		if (playerNickname === null) {
			// Don't set username
			playerNickname = "-1";
		}
		
		var player = {
			"playerId" : -1,
			"playerNickname" : playerNickname, 
			"team" : null,
			"cards" : null,
			"playerPosition" : null
		};
		
		var url = "http://127.0.0.1:8080/05_SampleBackend/rest/play/player/create";
		$.ajax({
			type: "POST",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "text/plain");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			data: JSON.stringify(player),
			contentType: "application/json; charset=UTF-8",
			success: function(result) {
				var playerId = result; 
				setLoggedUserCookie(playerId);
				$(".play-options").html("");
				$(".play-options").html("Your ID is " + playerId + ". You can <a href=\"initial_room.html\">join a room</a> now...");
			},
			error: function(xhr, status, error) {
				console.log("Problem " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
	}
	
	$(".btn-success").on("click", createPlayer);
	
});