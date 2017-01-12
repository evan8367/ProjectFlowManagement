var userService = {	
	webServiceUrl : {
		login : "/pfm-service/sso/user/login.do",
		register : "/pfm-service/sso/user/register.do",
		getUserInfo: "/pfm-service/sso/user/getUserInfo.do",
		logout:"/pfm-service/sso/user/logout.do"
	},
	login : function(username, password, isRemember, callback) {
		var isSuccess = false;
		$.post(this.webServiceUrl.login, {
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
		$.post(this.webServiceUrl.register, {
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
		$.get(this.webServiceUrl.getUserInfo, function(data, status) {
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
		$.get(this.webServiceUrl.logout, function(data, status) {
		});
	}
}