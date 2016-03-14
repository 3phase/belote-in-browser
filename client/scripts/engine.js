$(document).ready(function() {
	"use strict";

	var announce = null;
	var player_turn = 0;

	var game_container = $(".game-container");
	var colors = ["clubs", "diamonds", "hearts", "spades", "suit", "trumps"];
	var players = ["south", "east", "north", "west"];

	var cards_trumps = ["J", "9", "A", "10", "K", "Q", "8", "7"];

	var cards_suit = ["A", "10", "K", "Q", "J", "9", "8", "7"];

	function process_announce(color) {
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

	function distribute_cards() {
		game_container.html("");
		game_container.html("<strong>Announce: </strong>" + announce);
		var real_colors = colors.slice(0,4);
		var cards = {};
		$.each(real_colors, function(key, value) {
			if (announce == "trumps" || announce == value)
				cards[value] = cards_trumps;
			else
				cards[value] = cards_suit;
		});
		$.each(cards, function(key, value) {
			console.log(key + "=>" + value);
		}); 
	}

	game_container.html("<strong>Announce:</strong><br>");
	$.each(colors, function(key, value) {
		game_container.append("<button class='announce-color " + value + "'>" + value + "</button>");
	});
	game_container.append("<button class='announce-color pass'>Pass</button>")

	$(".announce-color").on("click", function() {
		var attr = $(this).attr("class");
		var color = attr.split(" ")[1];
		process_announce(color);
		player_turn += 1;
		if (player_turn > 3) {
			distribute_cards();
		}
	});

});