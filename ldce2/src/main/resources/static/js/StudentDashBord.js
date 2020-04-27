  $(document).ready(function(){
    var JsonPath = "../student/studentDashboard";
  
    var xhttp = new XMLHttpRequest();
    
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const data = JSON.parse(this.responseText);
      
       console.log("called js")


        if(data["faculty_approve"]=="1"){
            $("#cardstatus_registration").html("<strong>Approved</strong>");
        }
        else if(data["faculty_approve"]=="2"){
            $("#cardstatus_registration").html("<strong>Rejected</strong>");
            $("#comment_registration").css("display" , "block").append("<strong>"+data["faculty_comment"]+"</strong>");
        }
        else{
            $("#cardstatus_Registration").html("<strong>Pending</strong>");
        }

        for(var i=0;i<data["request"].length;i++){
            var x=data["request"][i];
        
            
        if(x["status1"]=="1"){
            if(x["status2"]=="1"){
                if(x["status3"]=="1"){ 
                    $("#cardstatus_"+x["type"]).html("<strong>Approved</strong>");
                }
                else if(x["status3"]=="2"){
                    
                    $("#cardstatus_"+x["type"]).html("<strong>Rejected</strong>");
                    $("#comment_"+x["type"]).css("display" , "block").append("<strong>"+x["faculty_comment"]+"</strong>");
                   
                }
                else{
                    $("#cardstatus_"+x["type"]).html("<strong>Pending At : Student Section</strong>");  
                    
                }
            }
            else if(x["status2"]=="2"){
                $("#cardstatus_"+x["type"]).html("<strong>Rejected</strong>");
                $("#comment_"+x["type"]).css("display" , "block").append("<strong>"+x["comment"]+"</strong>");
            }
            else{
              
                $("#cardstatus_"+x["type"]).html("<strong>Pending At : Cleark </strong>");  
            }
        }
        else if(x["status1"]=="2"){
            $("#cardstatus_"+x["type"]).html("<strong>Rejected</strong>");
            $("#comment_"+x["type"]).css("display" , "block").append("<strong>"+x["comment"]+"</strong>");
        }
        else{
            $("#cardstatus_"+x["type"]).html("<strong>Pending At : Department</strong>");  
        }
        
        }
    }
}
xhttp.open("GET", JsonPath, true);
xhttp.send();
    });
