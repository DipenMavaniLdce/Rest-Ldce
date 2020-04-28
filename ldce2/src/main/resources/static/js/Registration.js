function AjexSubmit(){

    // Get form
       
    var url ;
    var data;

    if($('#regForm').length) {
        var form = $('#regForm')[0];
    
        var data = new FormData(form);
        url= "/registerStudent"
    }
    else if($('#editProfileForm').length) {
        var form = $('#editProfileForm')[0];
        var data = new FormData(form);
        url= "/student/updateStudent"
    }
    else {
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
            window.location = '/student/'; 

        },
        error: function (e) {

                window.location = '/registerStudent'; 
        }
    });

}