var baseService = {
	post : function(url, param, callback) {
		$.post(url, param, function(data, status) {
			if(data.isSuccess === "F" && data.errorCode === "001") {
				throw new Error(data.errorCode+"|"+data.errorMessage);
			}

			if(callback) {
				callback(data, status);
			}
		});
	},
	get : function(url, callback) {
		$.get(url, function(data, status) {
			if(data.isSuccess === "F" && data.errorCode === "001") {
				throw new Error(data.errorCode+"|"+data.errorMessage);
			}

			if(callback) {
				callback(data, status);
			}
		});
	},

	ajaxSubmit : function(form, callback) {
		$("#upload_avatar_form").ajaxSubmit(function(data){
			if(data.isSuccess === "F" && data.errorCode === "001") {
				throw new Error(data.errorCode+"|"+data.errorMessage);
			}
			if(callback) {
				callback(data, 
					data.isSuccess === "S", 
					data.errorCode, 
					data.errorMessage);
			}
		});
	}
}