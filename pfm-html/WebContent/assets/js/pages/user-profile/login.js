$(document).ready(function() {
	
	var init = function() {
		$.getScript("/assets/js/pages/base-page.js",function(data, textStatus, jqxhr){
			$.getScript("/assets/js/service/base-service.js",function(data, textStatus, jqxhr){
				$.getScript("/assets/js/service/user-service.js",function(data, textStatus, jqxhr){
					$("#login_form").validationEngine({
						promptPosition : "centerRight", 
						autoPositionUpdate: true,
						binded: false
					});
					loadUsername();
					common.proventGoBack();
				});
			}); 
		}); 
	};

	var loadUsername = function() {
		var username = $("#username_text");
		var remember = $("#remember_checkbox");

		if (localStorage && localStorage.getItem("username")) {
			username.val(localStorage.getItem("username"));
			remember[0].checked = "checked";
		}
	};
	$("#login_button").click(function() {
        if ($("#login_form").validationEngine('validate') == false) {
        	$("#login_form").validationEngine();
        	return;
        }
		var username = $("#username_text").val();
		var password = $("#password_text").val();
		var remember = $("#remember_checkbox")[0].checked;
		userService.login(username, password, remember, function(data, isSuccess, errorCode, errorMessage) {
			if (isSuccess) {
				top.location.href = "../index.html";
			} else {
				alert(errorMessage);
			}
		});
	});


	init();
});