  $(document).ready(function(){
    var JsonPath = "http://localhost:8080/admin/adminDashbord";
  
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const data = JSON.parse(this.responseText);
            if(typeof(data.register)!='undefined'){
            	console.log(data);
            	$("#registrationCard").css("display" , "block");
            	$("#rcount").html(data.register);
            	$("#dcount").html(data.document);
            }
            else {
            	
            	$("#dcount").html(data.document);}

    }
}
xhttp.open("GET", JsonPath, true);
xhttp.send();
    });
