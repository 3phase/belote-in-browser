$(document).ready(function() {
	"use strict";
	
	$(".btn-success").on("click", function() {
		var playerId = $(".id").val();
		var playerNick = $(".nickname").val();
		
		if (playerNick === null) {
			// Don't set username
			playerNick = "-1";
		}
		
		
		
	});
	
});