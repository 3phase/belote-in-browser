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
		  return value != val;
		});
	}

	function getRandomCard() {
		var real_colors = colors.slice(0,4);
		var rand_color = real_colors[Math.floor(Math.random() * real_colors.length)];
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
		});
		console.log(players);
	}

	var game_container = $(".game-container");
	game_container.html("<strong>Announce:</strong><br>");
	$.each(colors, function(key, value) {
		game_container.append("<button class='announce-color " + value + "'>" + value + "</button>");
	});
	game_container.append("<button class='announce-color pass'>Pass</button>")
	distinguishCards();
	distributeCards(5);

	$(".announce-color").on("click", function() {
		var attr = $(this).attr("class");
		var color = attr.split(" ")[1];
		processAnnounce(color);
		player_turn += 1;
		if (player_turn > 3) {
			game_container.html("");
			game_container.html("<strong>Announce: </strong>" + announce);
			distributeCards(3);
		}
	});


});