$(document).ready(function(){
	
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	// get the required parameter
	const param = urlParams.get('error'); 
	if(param){
		$("#error_message").css("display" , "block")
	}
	
	
})

function loginChanged() {
		if($("#role").val()==="ADMIN") {
			$("#userId").html("Email:");
		}
		else {
			$("#userId").html("Enrollment No:");
		}
	}	