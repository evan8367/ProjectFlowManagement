$(document).ready(function() {
	
	var init = function() {
		$.getScript("/assets/js/service/user-service.js",function(data, textStatus, jqxhr){
			$("#register_form").validationEngine({
				promptPosition : "centerRight", 
				autoPositionUpdate: true,
				"custom_error_messages": {
					'#repeat_password_text' : {
						"equals": {
							"message": "Password should be same"
						}
					}
				}
			});
		}); 
	};
	
	$("#register_button").click(function() {
		if ($("#register_form").validationEngine('validate') == false) {
			$("#register_form").validationEngine();
			return;
		}
		
		var username = $("#username_text").val();
		var password = $("#password_text").val();
		var repeatedPassword = $("#repeat_password_text").val();
		var fullname = $("#fullname_text").val();
		var email = $("#email_text").val();
		var mobilePhone = $("#mobile_phone_text").val();
		userService.register(username, password, repeatedPassword, fullname, email, mobilePhone, 
			function(data, isSuccess, errorCode, errorMessage) {
			if (isSuccess) {
				alert("Register Success");
				top.location.href = "login.html";
			} else {
				alert(errorMessage);
			}
		});
	});


	init();
});