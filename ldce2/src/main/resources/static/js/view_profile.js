$( document ).ready(function() {
    console.log( "ready!" );

    var JsonPath = "/student/data";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const x = JSON.parse(this.responseText);

            showProfileData(x);
            showFormData(x);

            function showProfileData(x) {
                for(value in x) {
                    if (value == "info")
                        showProfileData(x.info);
                    else if (value == "guardian") 
                        showProfileData(x.guardian);
                    else if(value=="branch") {
                        var bval;
                    
                        switch(x[value]) {
                            
                            case 2: bval = "Automobile Engineering"; break;
                            case 3: bval = "Biomedical Engineering"; break;
                            case 5: bval = "Chemical Engineering"; break;
                            case 6: bval = "Civil Engineering"; break;
                            case 7: bval = "Computer Engineering"; break;
                            case 9: bval = "Electrical Engineering"; break;
                            case 11: bval = "Electronics & Communication Engineering"; break;
                            case 13: bval = "Environment Engineering"; break;
                            case 16: bval = "Information Technology"; break;
                            case 17: bval = "Instrumentation & Control Engineering"; break;
                            case 19: bval = "Mechanical Engineering"; break;
                            case 23: bval = "Plastic Technology"; break;
                            case 26: bval = "Rubber Technology"; break;
                            case 29: bval = "Textile Technology"; break;
                        } 
                        
                        console.log(x[value].toString());
                        $("#vbranch").html(bval);
                    }
                    else if(value=="student_photo" || value=="student_sign") {
                        $("#"+value).attr("src","data:image/jpg;base64,"+x[value]);
                    }
                    else if(value=="date_of_birth"){ $("#v"+value).html(x[value]);}
                    else {  
                
                        
                        $("#v"+value).html(x[value]);
                    }
                }
            }

            $("#fullname").html(x.first_name + " " + x.middle_name + " " + x.last_name);     
            $("#vaddress").html(x.guardian.off_add_l1 + "," + x.guardian.off_add_l2 + "</br>" + x.guardian.off_add_city + "," + x.guardian.off_add_state + "</br>" + x.guardian.off_add_country + "-" + x.guardian.off_add_pin_code);
            $("#vre_add").html(x.info.re_add_l1 + "," + x.info.re_add_l2 + "</br>" + x.info.re_add_city + "," + x.info.re_add_state + "</br>" + x.info.re_add_country + "-" + x.info.re_add_pin_code);
            $("#vpr_add").html(x.info.pr_add_l1 + "," + x.info.pr_add_l2 + "</br>" + x.info.pr_add_city + "," + x.info.pr_add_state + "</br>" + x.info.pr_add_country + "-" + x.info.pr_add_pin_code);
            
            function showFormData(x) {
                for (value1 in x) {
                    if (value1 == "info")
                        showFormData(x.info);
                    else if (value1 == "guardian") 
                        showFormData(x.guardian);
                    else if (value1 == "gender") {
                        $("#editProfileForm #" + x[value1]).attr("checked","true");
                    }
                    else if (value1 == "ssc_pr" || value1 == "hsc_pr" ) {
                        console.log(x[value1]);
                        $("#editProfileForm #" + value1).val(x[value1].toFixed(2));
                    }
            
                    else {
                        $("#editProfileForm #" + value1).val(x[value1]);
                    }

                }
            }

        }
    }
    xhttp.open("GET", JsonPath, true);
    xhttp.send();
});