$(document).ready(function() {
	"use strict";
	
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
			"cards" : null
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
				console.log("Success Haha " + result);
			},
			error: function(xhr, status, error) {
				console.log("Problem " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
	}
	
	$(".btn-success").on("click", createPlayer);
	
});