$(document).ready(function() {	
	var init = function() {
		$.getScript("/assets/js/service/base-service.js",function(data, textStatus, jqxhr){
			$.getScript("/assets/js/service/user-service.js",function(data, textStatus, jqxhr){
				loadUserInfo();
			});
		});
	};

	var loadUserInfo = function() {
		var username = $("#username_text");
		var fullname = $("#fullname_text");
		var fullnameLabel = $("#fullname_label");
		var email = $("#email_text");
		var newPassword = $("#new_password_text");
		var repeatPassword = $("#repeat_password_text");
		var mobilePhone = $("#mobile_phone_text");
		var moreInfo = $("#more_info_text");
		var avatarImg = $("#avatar_img");

		
		userService.getUserInfo(function(data, isSuccess, errorCode, errorMessage){
			var userInfo = data.userInfo;
			username.val(userInfo.username);
			fullname.val(userInfo.fullname);
			fullnameLabel.html(userInfo.fullname);
			email.val(userInfo.email);
			mobilePhone.val(userInfo.mobilePhone);
			moreInfo.val(userInfo.description);
			avatarImg.attr("src", userInfo.avatar);
		});
	};

	$("#upload_avatar_label").click(function() {
		$("#avatar_file").val("");
		$("#avatar_file").click();
	});

	$("#avatar_file").change(function(){
		var file = this.files;
		var windowURL = window.URL || window.webkitURL;
		if(file){
			dataURL = windowURL.createObjectURL(file[0]);
			$("#upload_avatar_button").show();
			$("#avatar_img").attr("src", dataURL);
		}
	});

	$("#upload_avatar_button").click(function(){
		userService.uploadAvatar($("#upload_avatar_form"), function(data, isSuccess, errorCode, errorMessage){
			alert("Uploaded");
			$("#avatar_img").attr("src", data.user.avatar);
		});
	})
	

	$("#change_button").click(function() {
		
	});

	init();
});