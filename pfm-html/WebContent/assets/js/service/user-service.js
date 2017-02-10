var userService = {
	webServiceUrl : {
		login : "/pfm-sso-service/user/login.do",
		register : "/pfm-sso-service/user/register.do",
		getUserInfo: "/pfm-sso-service/user/getUserInfo.do",
		logout:"/pfm-sso-service/user/logout.do",
		uploadAvatar:"/pfm-sso-service/user/uploadAvatar.do"
	},
	login : function(username, password, isRemember, callback) {
		var isSuccess = false;
		baseService.post(this.webServiceUrl.login, {
			username: username,
			password: password
		}, function(data, status) {
			
			if(data.isSuccess === "S") {
				isSuccess = true;
				if(isRemember) {
					localStorage.setItem("username", username);
				}

				sessionStorage.setItem("username", username);
			}
			if(callback) {
				callback(data, 
					isSuccess, 
					data.errorCode, 
					data.errorMessage);
			}
		});
	},

	register : function(username, password, repeatedPassword, fullname, email, mobilePhone, callback) {
		var isSuccess = false;
		baseService.post(this.webServiceUrl.register, {
			username: username,
			password: password, 
			repeatedPassword:repeatedPassword,
			fullname:fullname, 
			email:email, 
			mobilePhone:mobilePhone
		}, function(data, status) {
			if(callback) {
				callback(data, 
					data.isSuccess === "S", 
					data.errorCode, 
					data.errorMessage);
			}
		});
	},
	
	getUserInfo : function(callback) {
		var isSuccess = false;

		baseService.get(this.webServiceUrl.getUserInfo, function(data, status) {
			console.log(JSON.stringify(data));
			//Need login
			if(data.errorCode === "001") {
				localStorage.removeItem("username");
			}
			if(callback) {
				callback(data, 
					data.isSuccess === "S", 
					data.errorCode, 
					data.errorMessage);
			}
			
		});
	},

	logout : function() {
		var isSuccess = false;
		baseService.get(this.webServiceUrl.logout, function(data, status) {});
	},

	uploadAvatar : function(form, callback) {
		baseService.ajaxSubmit($("#upload_avatar_form"), function(data){
			if (data.isSuccess == "S") {
				if(callback) {
					callback(data, 
						data.isSuccess === "S", 
						data.errorCode, 
						data.errorMessage);
				}
			}
		});
		
	}
}