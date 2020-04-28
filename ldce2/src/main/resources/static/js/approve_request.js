$( document ).ready(function() {
    var JsonPath = "/admin/pendingRegList";
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            const x = JSON.parse(this.responseText);

            showTableData(x);
            
            $("#searchText").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $(".TableRow").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });

            function showTableData(x) {
                for(var i=0;i<x.length;i++) {
                    
                    var enroll= x[i].enrollment 
                        name = x[i].first_name + " " + x[i].middle_name + " " + x[i].last_name,
                        sem = x[i].semester,
                        branch = x[i].branch;
                        gmail=x[i].email;
                    var n =
                    `<tr class='TableRow'>
                    <td>`+(i+1)+`</td>
                    <td>`+enroll+`</td>
                    <td>`+name+`</td>
                    <td>`+sem+`</td>
                    <td>Profile</td>
                    <td>
                        <button class="btn btn-info openStudentModal" data-toggle="modal" data-target="#view_profile"  data-id="`+enroll+`">View Profile</button>
                    </td>
                    <td><button class="btn btn-success btnClick" name="1"  id=`+enroll+`>Approve</button></td>
                    <td>
                        <input type="text" class="form-control" id="comment`+enroll+`" name="unapp_comment"  placeholder="Unapprove Comment" required>
                        <button  value="Unapprove" class="btn btn-danger btnClick" name="0" id=`+enroll+`>Disapprove</button>
                    </td>
                    </tr>`

                    $("#request_table").append(n);

                }
            }
            $(".btnClick").click(function() {
                var btnId=this;
                var c=$(".btnClick");
                var comment_value = $("#comment"+this.id).val();
                var dataString1={enrollment:this.id,status:this.name,comment:comment_value} ;
                console.log(dataString1);
                    dataString = JSON.stringify(dataString1);
                    alert(dataString);
                    console.log(dataString1);
                    url="/admin/facultyApprove";
                    $.ajax({
                        type: "post",
                        url: url,
                        data: dataString1,
                        success: function (data) {
                        alert("success");
                        $(btnId).closest('tr').remove();
                        },
                        error: function (jqXHR, exception) {
                            var msg = '';
                            if (jqXHR.status === 0) {
                                msg = 'Not connect.\n Verify Network.';
                            } else if (jqXHR.status == 404) {
                                msg = 'Requested page not found. [404]';
                            } else if (jqXHR.status == 500) {
                                msg = 'Internal Server Error [500].';
                            } else if (exception === 'parsererror') {
                                msg = 'Requested JSON parse failed.';
                            } else if (exception === 'timeout') {
                                msg = 'Time out error.';
                            } else if (exception === 'abort') {
                                msg = 'Ajax request aborted.';
                            } else {
                                msg = 'Uncaught Error.\n' + jqXHR.responseText;
                            }
                        
                        alert(msg);
                        }
                    })
                    return false;
                });
            
            $(".openStudentModal").click(function() {
                var enroll = $(this).data("id");
                console.log(x[0].enrollment);
                for(var i=0;i<x.length;i++)
                    if(x[i].enrollment==enroll)
                        break;
                showProfileData(x[i]);
            
                var fullname = x[i].first_name + " " + x[i].middle_name + " " + x[i].last_name;

                $("#fullname").html(fullname);     
                $("#voff_add").html(x[i].guardian.off_add_l1 + "," + x[i].guardian.off_add_l2 + "</br>" + x[i].guardian.off_add_city + "," + x[i].guardian.off_add_state + "</br>" + x[i].guardian.off_add_country + "-" + x[i].guardian.off_add_pin_code);
                $("#vre_add").html(x[i].info.re_add_l1 + "," + x[i].info.re_add_l2 + "</br>" + x[i].info.re_add_city + "," + x[i].info.re_add_state + "</br>" + x[i].info.re_add_country + "-" + x[i].info.re_add_pin_code);
                $("#vpr_add").html(x[i].info.pr_add_l1 + "," + x[i].info.pr_add_l2 + "</br>" + x[i].info.pr_add_city + "," + x[i].info.pr_add_state + "</br>" + x[i].info.pr_add_country + "-" + x[i].info.pr_add_pin_code);

                $("#view_profile_title").html(fullname);
            });
        
            function showProfileData(x) {
                for(value in x) {
                    if (value == "info")
                        showProfileData(x.info);
                    else if (value == "guardian") 
                        showProfileData(x.guardian);
                    else if(value=="branch") {
                        var bval;
                        switch(x[value]) {
                            case 02: bval = "Automobile Engineering"; break;
                            case 03: bval = "Biomedical Engineering"; break;
                            case 05: bval = "Chemical Engineering"; break;
                            case 06: bval = "Civil Engineering"; break;
                            case 07: bval = "Computer Engineering"; break;
                            case 09: bval = "Electrical Engineering"; break;
                            case 11: bval = "Electronics & Communication Engineering"; break;
                            case 13: bval = "Environment Engineering"; break;
                            case 16: bval = "Information Technology"; break;
                            case 17: bval = "Instrumentation & Control Engineering"; break;
                            case 19: bval = "Mechanical Engineering"; break;
                            case 23: bval = "Plastic Technology"; break;
                            case 26: bval = "Rubber Technology"; break;
                            case 29: bval = "Textile Technology"; break;
                        }
                        $("#vbranch").html(bval);
                    }
                    else if(value=="student_photo" || value=="student_sign") {
                        $("#"+value).attr("src","data:image/jpg;base64,"+x[value]);
                    }
                    else {
                        $("#v"+value).html(x[value]);
                    }
                }
            }
        }
    }
    xhttp.open("GET", JsonPath, true);
    xhttp.send();
});