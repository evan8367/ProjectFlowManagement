$(document).ready(function() {
	window.onerror = function(messageOrEvent, source, lineno, colno, error) {
		if(error.message.indexOf("|",0) > -1) {
			var errorCode = error.message.split("|")[0];
			var errorMessage = error.message.split("|")[1];
			if(errorCode === "001") {
				top.location.href="/user-profile/login.html";
			}
			return true;
		}
		return false;
	}
});