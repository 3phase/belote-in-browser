$(document).ready(function() {
	"use strict";

	function createRoom() {
		console.log("Creating room....");
	}
	
	function joinRoom() {
		
	}
	
	function getRooms() {
		
	}
	
	$(".playOpt.create-room").on("click", createRoom);
	$(".playOpt.join-room").on("click", joinRoom);
	$(".playOpt.see-available").on("click", getRooms);
	
});