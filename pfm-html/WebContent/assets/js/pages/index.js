$(document).ready(function(){
	var init = function() {
		$.getScript("/assets/js/service/base-service.js",function(data, textStatus, jqxhr){
			$.getScript("/assets/js/service/user-service.js",function(data, textStatus, jqxhr){

				$("#create_project_form").validationEngine({
					promptPosition : "bottomLeft", 
					autoPositionUpdate: true
				});

				$('#purposed_start_date_text').datepicker({
					autoclose: true
				});
				$('#purposed_end_date_text').datepicker({
					autoclose: true
				});

				$("#project_color_text").spectrum({
					color: "#f00",
					change: function(color) {
						$("#basic-log").text("change called: " + color.toHexString());
					}
				});
				loadInfo();
			});
		});
	};

	var loadInfo = function() {
		var projects = [{id:1,
			name:"Gest1",
			description:"Test1"},{id:2,
				name:"Test2",
				description:"Test2"}];

				$("#project_item_template").tmpl(projects, {
					getFirstLetter: function () {
						return this.data.name.substr(0,1);
					}
				}).appendTo("#project_collection");
			} 

			$("#new_project_button").click(function(t){
				t.preventDefault();
				$("#new_project_modal").modal("show");
			});

			$("#save_new_project_button").click(function(){
				if ($("#create_project_form").validationEngine('validate') == false) {
					return;
				}
			});

			init();
		});


