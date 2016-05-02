$(document).ready(function() {
	
	"use strict";
	
	var roomStatus = false;
	var roomId = -1;
	var userId = -1;
	var canvasDefault = $(".game-container").clone();
	
	function serverCardChange(mark, type, owner) {
		var teamId = Cookies.get("team-token") - 1;
		var url = "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + Cookies.get("room-token") + "/t/" + teamId + "/player/" + Cookies.get("user-token") + "/play-card";
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
	
	function addCardToCommonTable(mark, type, owner, cardItself) {
		var commonDesk = $(".common-card-holder");
		serverCardChange(mark, type, owner);
		$(cardItself).remove();
		commonDesk.append("<button class=\"card\" data-card-type=\"" + type + "\" " +
					"data-card-mark=\"" + mark + "\" data-card-owner=\"" +
							owner + "\">" + type + " " + mark
							+ "</button>")
	}
	
	function handleCardChoice() {
		$(".card").on("click", function() {
			// Get player whom turn ough to be next
			if ($(this).attr("data-card-owner") != Cookies.get("user-token")) {
				alert("It's not your turn!");
			} else {
				var cardType = $(this).attr("data-card-type");
				var cardMark = $(this).attr("data-card-mark");
				var cardOwner = $(this).attr("data-card-owner");
				addCardToCommonTable(cardMark, cardType, cardOwner, $(this));
			}
		});
	}
	
	function dumpCardsOnTable(cards, position) {
		var specCardHolder = ".card-holder." + position;
		var allCardHolders = $(".card-holder");
		$.each(allCardHolders, function() {
			for (var i = 0; i < 8; i++) {
				$(this).append("<button class=\"unknown\" disabled>Unknown</button>");
			}
		});
		$(specCardHolder).html("");
		cards = JSON.parse(cards);
		$.each(cards, function(cardNum, data) {
			var newCardUI = "<button class=\"card\" data-card-type=\"" + data.type + "\" " +
					"data-card-mark=\"" + data.mark + "\" data-card-owner=\"" +
							data.owner + "\">" + data.type + " " + data.mark
							+ "</button>";
			$(specCardHolder).append(newCardUI);
		});
		handleCardChoice();
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