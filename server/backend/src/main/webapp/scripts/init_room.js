$(document).ready(function() {
	"use strict";
	
	var roomId = -1;
	
	function addUserToRoom() {
		var userId = Cookies.get("user-token");
		var url = "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/" + roomId + "/add-player/";
		$.ajax({
			type: "PUT",
			url: url,
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Content-Type", "text/plain");
			},
			data: userId.toString(),
			contentType: "text/plain; charset=UTF-8",
			success: function(result) {
				console.log("Successful PUT request");
			},
			error: function(xhr, status, error) {
				console.log("Problem " + JSON.stringify(xhr) + "; " + status + "; " + error);
				$(document).html("Error 5xx - A problem occurred. Cannot continue.");
				return false;
			}
		});
	}
	
	function createRoom() {
		var actionField = $(".play-options");
		
		actionField.html("");
		
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/create",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "text/plain");
			},
			success: function(result) {
				roomId = result;
				addUserToRoom();
				actionField.append("Room with ID <strong>" + roomId + "</strong> was created. You can now <a href=\"game.html\">start playing</a>...");
				if (Cookies.get("room-token") != undefined) {
					Cookies.remove("room-token");
				}
				Cookies.set("room-token", roomId, {path: "game.html"});
			},
			error: function(xhr, status, error) {
				actionField.append("Problem while creating room. " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
		
	}
	
	function joinRoom() {
		
	}
	
	function getRooms(event) {
		var actionField = $(".play-options");
		actionField.html("");
		
		$.ajax({
			type: "GET",
			url: "http://127.0.0.1:8080/05_SampleBackend/rest/play/room/all",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("accept", "application/json");
			},
			success: function(result) {
				if (event === "fetch") {
					fetchData($(result), actionField);
				}
			},
			error: function(xhr, status, error) {
				actionField.append("Problem while fetching rooms. " + JSON.stringify(xhr) + "; " + status + "; " + error);
			}
		});
	}
	
	function fetchData(data, actionField) {
		data = JSON.stringify(data);
		var jsonData = JSON.parse(data);
		console.log("Data length " + jsonData.length);
		
		var resultLength = jsonData.length;		
		delete jsonData.length;
		
		$.each(jsonData, function(key, value) {
			actionField.append("<div class=\"room\">" + key + "</div>");
		});
		
	}
	
	if (Cookies.get("user-token") == undefined) {
		// User not logged
		var actionField = $(".play-options");
		actionField.html("");
		actionField.html("You must <a href=\"index.html\">set your user credientials</a> first.");
		return false;
	}
	$(".playOpt.create-room").on("click", createRoom);
	$(".playOpt.join-room").on("click", joinRoom);
	$(".playOpt.see-available").on("click", function() {
		getRooms("fetch");
	});
	
});