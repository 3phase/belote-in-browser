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
		// Send request to server to check player cards
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
				$(".card-holder."+player).append("<button data-card-type='" + card_color +
														+ "' data-card='" + card + "'>" +card_color + " " + card + "</button>");
				
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

	function await_card_on_desk() {
		var expected_input = ".card-holder." + player_turns[next_turn];
		$($(expected_input).on("click", function() {
			if (next_turn < 3)
				next_turn += 1;
			else
				next_turn = 0;
			await_card_on_desk();
		}));
	}

	function clean_playing_screen() {
		announce_bar.html("");
		announce_bar.html("<h3>Announce: " + announce + "</h3>");
	}

	function begin_game() {
		game_container.html(original_html);
		clean_playing_screen();
		distributeCards(3);
		dumpCardsOnDesk();
		await_card_on_desk();
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

	$(".announce-color").on("click", function() {
		var attr = $(this).attr("class");
		var color = attr.split(" ")[1];
		processAnnounce(color);
		player_turn += 1;
		if (player_turn > 3) {
			begin_game();
		}
	});


});