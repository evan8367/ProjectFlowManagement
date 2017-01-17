$(document).ready(function() {
	var init = function() {
		$.getScript("/assets/js/pages/base-page.js",function(data, textStatus, jqxhr){
			$.getScript("/assets/js/service/base-service.js",function(data, textStatus, jqxhr){
				$.getScript("/assets/js/service/user-service.js",function(data, textStatus, jqxhr){
					loadUserInfo();
				});
			});
		});
	};

	var loadUserInfo = function() {
		var username = $("#navigation_username_label");
		var avatar = $("#navigation_avatar_img");

		userService.getUserInfo(function(data, isSuccess, errorCode, errorMessage){
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