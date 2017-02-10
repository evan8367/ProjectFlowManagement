var projectService = {
	webServiceUrl : {
		getProjectList : "/pfm-project-service/project/project/getProjectList.do",
		createProject : "/pfm-project-service/project/project/createProject.do"
	},

	getProjectList : function(callback) {
		baseService.get(this.webServiceUrl.getProjectList, function(data, status) {
			console.log(JSON.stringify(data));
			if(callback) {
				callback(data, 
					data.isSuccess === "S", 
					data.errorCode, 
					data.errorMessage);
			}
		});
	},

	createProject : function(projectName, purposedStartDate, purposedEndDate, description, color, callback) {
		baseService.post(this.webServiceUrl.getProjectList, {
			projectName : projectName, 
			purposedStartDate : purposedStartDate, 
			purposedEndDate : purposedEndDate, 
			description : description, 
			cover : color
		}, function(data, status) {
			console.log(JSON.stringify(data));
			if(callback) {
				callback(data, 
					data.isSuccess === "S", 
					data.errorCode, 
					data.errorMessage);
			}
		});
	}
};