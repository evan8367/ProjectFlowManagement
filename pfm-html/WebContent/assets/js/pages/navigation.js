$(document).ready(function() {
	var init = function() {
		$.getScript("/assets/js/service/user-service.js",function(data, textStatus, jqxhr){
			loadUserInfo();
		});
	};

	var loadUserInfo = function() {
		var username = $("#navigation_username_label");
		var avatar = $("#navigation_avatar_img");

		userService.getUserInfo(function(data, isSuccess, errorCode, errorMessage){
			if(!isSuccess) {
				alert(errorMessage);
				if(errorCode === "001") {
					top.location.href="/user-profile/login.html";
				}
			}
			var userInfo = data.userInfo;
			username.html(userInfo.username);
			avatar.attr("src", userInfo.avatar);
		});
	};

	$("#logout_button").click(function(){
		userService.logout();
		top.location.href = "/user-profile/login.html";
	});

	init();
});