$(document).ready(function() {	
	var init = function() {
		$.getScript("/assets/js/service/user-service.js",function(data, textStatus, jqxhr){
			loadUserInfo();
		});
	};

	var loadUserInfo = function() {
		var username = $("#username_text");
		var fullname = $("#fullname_text");
		var email = $("#email_text");
		var newPassword = $("#new_password_text");
		var repeatPassword = $("#repeat_password_text");
		var mobilePhone = $("#mobile_phone_text");
		var moreInfo = $("#more_info_text");

		userService.getUserInfo(function(data, isSuccess, errorCode, errorMessage){
			if(!isSuccess) {
				if(errorCode === "001") {
					return;
				}
			}
			var userInfo = data.userInfo;
			username.val(userInfo.username);
			fullname.val(userInfo.fullname);
			email.val(userInfo.email);
			mobilePhone.val(userInfo.mobilePhone);
			moreInfo.val(userInfo.description);
		});
	};

	$("#change_button").click(function() {
		
	});

	init();
});