$(document).ready(function() {
	"use strict";

	var announce = null;

	var colors = ["clubs", "diamonds", "hearts", "spades", "suit", "trump"];

	var cards_trump = {
		"J": 20,
		"9": 14,
		"A": 11,
		"10": 10,
		"K": 4,
		"Q": 3,
		"8": 0,
		"7": 0
	};

	var cards_suit = {
		"A": 11,
		"10": 10,
		"K": 4,
		"Q": 3,
		"J": 2,
		"9": 0,
		"8": 0,
		"7": 0	
	};

	function process_announce(color) {
		var color_idx = $.inArray(color, colors);
		var buttons = $(".announce-color")
		for (var i = 0; i < color_idx; i++) {
			var button_name = "." + colors[i];
			$(button_name).prop("disabled", true);
		}
		announce = colors[color_idx];

	}

	var game_container = $(".game-container");
	game_container.html("<strong>Announce:</strong><br>");
	$.each(colors, function(key, value) {
		game_container.append("<button class='announce-color " + value + "'>" + value + "</button>");
	});

	$(".announce-color").on("click", function() {
		var attr = $(this).attr("class");
		var color = attr.split(" ")[1];
		process_announce(color);
	});

});