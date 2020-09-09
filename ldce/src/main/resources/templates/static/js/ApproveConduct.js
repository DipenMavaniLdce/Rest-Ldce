$( document ).ready(function() {	

    var JsonPath = "/admin/pendingDocument";
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        
        if (this.readyState == 4 && this.status == 200) {
            const x = JSON.parse(this.responseText);
    
            console.log(x);
            showTableData(x);
        
            $("#searchText").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $(".TableRow").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        
            function showTableData(x) {

                var certiType = $("#certiType").text();
                for(var i=0;i<x.length;i++) {

                    if(x[i].type == "conduct") {

                    var enroll= x[i].enrollment,
                        requestid=x[i].request_id,
                        name = x[i].first_name + " " + x[i].middle_name + " " + x[i].last_name,
                        sem = x[i].semester,
                        branch = x[i].branch,
                        gmail=x[i].email;
                        type=x[i].type;
                        enrollment = x[i].enrollment;
                        
                    var n =
                    `<tr class='TableRow' id=`+enrollment+`>
                    <td>`+(i+1)+`</td>
                    <td>`+enroll+`</td>
                    <td>`+name+`</td>
                    <td>`+sem+`</td>
                    <td id="`+type+``+enroll+`" name=`+type+`>`+type+`</td>
                    <td>
                        <button class="btn btn-info openDocumentModal" data-toggle="modal" data-target="#View_Document"  data-id="`+type+"-"+enroll+`">View Document</button>
                    </td>
                    <td>
                        <button class="btn btn-info openFeeReceipt" data-toggle="modal" data-target="#Fee_Receipt"  data-id="`+type+"-"+enroll+`">Fee Receipt</button>
                    </td>
                    <td>
                        <button class="btn btn-info openMarksheet" data-toggle="modal" data-target="#marksheet"  data-id="`+type+"-"+enroll+`">Marksheet</button>
                    </td>
                    <td>
                        <button class="btn btn-success btnClick" name="approve"  id=`+type+`_`+enroll+`>Approve</button>
                    <td>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control comment`+enroll+`"  id="comment`+type+``+enroll+`" name=`+type+`  placeholder="Unapprove Comment" required>
                            <div class="input-group-append">
                                <button  value="Unapprove" class="btn btn-danger btnClick" name="disapprove" id=`+type+`_`+enroll+`>disapprove</button>
                            </div>
                        </div>
                    </td>
                    </tr>`

                    $("#request_table").append(n);

                    }

                }
            }

            $(".openDocumentModal").click(function() {
                var id = $(this).data("id").split("-");
                for(var i=0;i<x.length;i++){
                    if(x[i].enrollment==id[1])
                        if(x[i].type==id[0])
                            break;
                }

                $student = {
                    name:x[i].first_name + " " + x[i].middle_name + " " + x[i].last_name,
                    enrollment: x[i].enrollment,
                    gender:"Mr",
                    branch:"Information Technology",
                    sem:x[i].semester,
                    course:x[i].course,
                    addmissionYear:x[i].addmissionYear,
                    graduationYear:x[i].graduationYear,
                    cgpa:x[i].cgpa,
                    rank: x[i].ranks,
                }

                if(x[i].gender == "male")
                    $student.gender = "Mr";
                else if(x[i].gender == "female")
                    $student.gender = "Miss";
                else
                    $student.gender = "";

                var bval="";
                switch(x[i].branch) {  
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
                $student.branch = bval;
                
                $("#viewCertificate").html(generateCertificate(x[i].type, $student));
            });

            $(".openFeeReceipt").click(function() {
                var id = $(this).data("id").split("-");
                console.log(x[0].enrollment);
                for(var i=0;i<x.length;i++){
                    if(x[i].enrollment==id[1])
                        if(x[i].type==id[0])
                            break;
                }
                    
                $('#fee_Receipt').attr("src","data:image/jpg;base64,"+x[i].fee_Receipt);
            });

            $(".openMarksheet").click(function() {
                var id = $(this).data("id").split("-");
                console.log(x[0].enrollment);
                for(var i=0;i<x.length;i++){
                    if(x[i].enrollment==id[1])
                        if(x[i].type==id[0])
                            break;
                }
                    
                $('#marksheet').attr("src","data:image/jpg;base64,"+x[i].marksheet);
            });
        
            
            $(".btnClick").click(function() {
                var btnId=this;
                var id = btnId.id;
                var c=$(".btnClick");
                
                var typearray= id.split('_')
                var typ = typearray[0];
                
                var enrollment = typearray[1];
                
                var com = $("#comment"+typ+enrollment).val();
                
                var dataString1={enrollment:enrollment,type:typ,status:this.name,comment:com} ;
            
                    dataString = JSON.stringify(dataString1);

                    url= "/admin/DocumentApprove";
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
        
        }
    };
    xhttp.open("GET", JsonPath, true);
    xhttp.send();
});