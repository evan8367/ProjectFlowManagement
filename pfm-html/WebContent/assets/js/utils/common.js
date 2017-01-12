var common = {
	getBasePath : function() {
		var location = (window.location+'').split('/');  
		var basePath = location[0]+'//'+location[2]+'/'+location[3];
		return basePath;
	},

	proventGoBack : function () {
		history.pushState(null, null, document.URL);
	    window.addEventListener('popstate', function () {
	        history.pushState(null, null, document.URL);
	    });
	}
}