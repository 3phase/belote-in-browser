$(document).ready(function() {
	
	"use strict";
	
	var roomStatus = false;
	var roomId = -1;
	var userId = -1;
	var canvasDefault = $(".game-container").clone();
	
	function dumpCardsOnTable(cards, position) {
		var specCardHolder = ".card-holder." + position;
		var allCardHolders = $(".card-holder");
		$.each(allCardHolders, function() {
			for (var i = 0; i < 5; i++) {
				$(this).append("<button class=\"unknown\" disabled>Unknown</button>");
			}
		});
		$(specCardHolder).html("");
//		$(specCardHolder).append("Az sam tuk");
		cards = JSON.parse(cards);
		$.each(cards, function(cardNum, data) {
			var newCardUI = "<button class=\"card\" data-card-type=\"" + data.type + "\" " +
					"data-card-mark=\"" + data.mark + "\" data-card-owner=\"" +
							data.owner + "\">" + data.type + " " + data.mark
							+ "</button>";
			$(specCardHolder).append(newCardUI);
//			console.log(data.type);
		});
	}
	
	function requestCards() {
		var teamId = Cookies.get("team-token") - 1;
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + Cookies.get("room-token") + "/t/" + teamId + "/player/" + Cookies.get("user-token") + "/get-cards",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "application/json");
			},
			success: function(result) {
				console.log(JSON.stringify($(result)));
				var receivedCards = JSON.stringify($(result));
				getUserPosition(receivedCards);
			},
			error: function(xhr, status, error) {
				console.log("Problem while creating room. " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
	}
	
	function getUserPosition(cards) {
		var teamId = Cookies.get("team-token") - 1;
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + Cookies.get("room-token") + "/t/" + teamId + "/player/" + Cookies.get("user-token") + "/get-position",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "text/plain");
			},
			success: function(result) {
				var userPosition = result;
				dumpCardsOnTable(cards, userPosition);
			},
			error: function(xhr, status, error) {
				console.log("Problem while creating room. " + JSON.stringify(xhr) + "; " + status + "; " + error);
				return false;
			}
		});
	}
	
	function checkIfRoomDone() {
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + Cookies.get("room-token") + "/is-available",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "text/plain");
			},
			success: function(result) {
				if (result == 1) { 
					roomStatus = true;
					/*clearInterval(interval);*/
					$(".game-container").replaceWith(canvasDefault);
					requestCards();
					var cardholders = $(".card-holder");
					$.each(cardholders, function () {
						if ($(this).attr("data-user-id") === undefined) {
							$(this).attr("data-user-id", userId);
							return false;
						}
					});
				}
			},
			error: function(xhr, status, error) {
				console.log("Problem while creating room. " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
	}
	
	
	if (Cookies.get("user-token") === undefined || Cookies.get("room-token") === undefined) {
		$(".game-container").html("");
		$(".game-container").html("Fill your <a href=\"initial_room.html\">data</a> first.");
		return false;
	}
	
	roomId = Cookies.get("room-token");
	userId = Cookies.get("user-token");

	if (roomStatus == false) {
		$(".game-container").html("");
		$(".game-container").html("Waiting for the required amount of players to gather...");
		checkIfRoomDone();
		/*var interval = setInterval(checkIfRoomDone, 5000);*/
	}
	
});