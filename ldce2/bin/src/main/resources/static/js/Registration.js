function AjexSubmit(){
var url ;
var data;
if($('#regForm').length){
	 var form = $('#regForm')[0];
 
     var data = new FormData(form);
    url= "http://localhost:8080/registerStudent"
}
else if($('#editProfileForm').length){
			var form = $('#editProfileForm')[0];
	 		var data = new FormData(form);
    		url= "http://localhost:8080/student/updateStudent"
}
else
{
	alert("some thing went wrong")
	}

      

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: url,
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 60000,
            success: function (data) {

              alert("data submitted");
              window.location = 'http://localhost:8080/student/'; 

            },
            error: function (e) {

            	  window.location = 'http://localhost:8080/registerStudent'; 
            }
        });

 

}