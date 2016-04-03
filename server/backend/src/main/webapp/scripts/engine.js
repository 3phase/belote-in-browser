$(document).ready(function() {
	"use strict";

	var announce = null;
	var player_turn = 0;

	var colors = ["clubs", "diamonds", "hearts", "spades", "suit", "trumps"];
	var players = {
		"south": [], 
		"east": [], 
		"north": [], 
		"west": []
	};

	var cards_trumps = ["J", "9", "A", "10", "K", "Q", "8", "7"];
	var cards_suit = ["A", "10", "K", "Q", "J", "9", "8", "7"];

	var player_turns = ["south","east", "north", "west"];
	var next_turn = 0;

	var cards = {};

	function processAnnounce(color) {
		var color_idx = $.inArray(color, colors);
		var buttons = $(".announce-color")
		for (var i = 0; i <= color_idx; i++) {
			var button_name = "." + colors[i];
			$(button_name).prop("disabled", true);
		}
		if(color != "pass") {
			announce = colors[color_idx];
		}

	}

	function check_if_combination_avilable(player_key) {
		// TODO: Send request to server to check player cards
	}

	function distinguishCards() {
		var real_colors = colors.slice(0,4);
		$.each(real_colors, function(key, value) {
				cards[value] = cards_suit;
		});
		$.each(cards, function(key, value) {
			console.log(key + "=>" + value);
		}); 
	}

	function remove_card_from_common_deck(color, val) {
		cards[color] = jQuery.grep(cards[color], function(value) {
		 	return(value != val);
		});
	}

	function getRandomCard() {
		var real_colors = null;
		var rand_color = getRandomCardsColor();
		var rand_color_cards = cards[rand_color];
		var rand_card = rand_color_cards[Math.floor(Math.random() * rand_color_cards.length)];
		remove_card_from_common_deck(rand_color, rand_card);
		return(rand_color + ":" + rand_card);
	}

	function distributeCards(count) {
		$.each(players, function(key, value) {
			for (var i=0; i < count; i++) {
				var card = getRandomCard();
				players[key].push(card);
			}
			if (count == 3) {
				check_if_combination_avilable(key);
			}
		});
	}

	function dumpCardsOnDesk() {
		$.each(players, function(k, v) {
			var player = k;
			var value = players[player];
			$.each(value, function(key, val) {
				var card_color = val.split(":")[0];
				var card = val.split(":")[1];
				$(".card-holder."+player).append("<button data-card-type='" + card_color + "' data-card='" + card + "'>" +card_color + " " + card + "</button>");
				
			});
		});
	}

	function getRandomCardsColor() {
		var keys = Object.keys(cards);
		var rand_color = null;
		do {
			rand_color = keys[Math.floor(Math.random() * keys.length)];
		} while (cards[rand_color].length == 0);
		return(rand_color);
	}

	function clean_playing_screen() {
		announce_bar.html("");
		announce_bar.html("<h3>Announce: " + announce + "</h3>");
	}
	
	function blockOthersAndReleaseCurrentsCards() {
		$(".card-holder button").prop("disabled", true);
		var expected_input = ".card-holder." + player_turns[next_turn];
		$(".card-holder." + player_turns[next_turn] + " button").prop("disabled", false);
	}

	function begin_game() {
		game_container.html(original_html);
		clean_playing_screen();
		distributeCards(3);
		dumpCardsOnDesk();
		blockOthersAndReleaseCurrentsCards();
	}
	
	function handleCardChoice() {
		var cardType = $(this).attr("data-card-type");
		var card = $(this).attr("data-card");
		var originatingPlayer = $(this).parent().attr("class");
		originatingPlayer = originatingPlayer.split(" ")[1];
		if (next_turn < 3) {
			next_turn += 1;
		} else {
			var commonDesk = $(".common-card-holder button");
			sendCommonTableCards(commonDesk);
			next_turn = 0;
		}
		placeCardOnCommonTable(cardType, card, originatingPlayer);
		blockOthersAndReleaseCurrentsCards();
	}
	
	function placeCardOnCommonTable(cardType, card, originatingPlayer) {
		var cardToRemove = $("[data-card-type=" + cardType + "][data-card=" + card + "]");
		cardToRemove.attr("data-card-owner", originatingPlayer);
		$(".common-card-holder").append(cardToRemove);
		$(".card-holder button[data-card-type=" + cardType + "][data-card=" + card + "]").remove();
	}
	
	function sendCommonTableCards(cards) {
		var cardsOnTable = []
		var ENDPOINT = "http://77.70.100.163:8080/05_SampleBackend/rest/play/room/1/evaluate_cards"
		$.each(cards, function(key, val) {
			cardsOnTable[$(val).attr("data-card-owner")] = []
			cardsOnTable[$(val).attr("data-card-owner")][0] = $(val).attr("data-card-type");
			cardsOnTable[$(val).attr("data-card-owner")][1] = $(val).attr("data-card"); 
		});
		
		$.ajax(ENDPOINT, {
			method: "POST",
			dataType: "json",
			data: JSON.stringify(cardsOnTable),
			contentType: "application/json; charset=utf-8"
		}).then(function(result) {
			console.log($(result));
		});
	}
	
	function handleAnnounce() {
		var attr = $(this).attr("class");
		var color = attr.split(" ")[1];
		processAnnounce(color);
		player_turn += 1;
		if (player_turn > 3) {
			game_container.html(original_html);
			begin_game();
		}
	}

	var game_container = $(".game-container");
	var original_html = $(".game-container").html();
	var announce_bar = $(".announce-bar");
	game_container.html("<strong>Announce:</strong><br>");
	$.each(colors, function(key, value) {
		game_container.append("<button class='announce-color " + value + "'>" + value + "</button>");
	});
	game_container.append("<button class='announce-color pass'>Pass</button>")
	
	distinguishCards();
	distributeCards(5);
	
	$(".announce-color").on("click", handleAnnounce);
	$("body").on("click", ".card-holder button", handleCardChoice);


});