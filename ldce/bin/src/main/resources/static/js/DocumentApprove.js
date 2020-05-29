$( document ).ready(function() {	

    var JsonPath = "/admin/Document";
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
    
                for(var i=0;i<x.length;i++) {
                    
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
                        <button class="btn btn-info openStudentModal" data-toggle="modal" data-target="#View_Document"  data-id="`+enroll+`">View Document</button>
                    </td>
                    <td>
                        <button class="btn btn-info openFeeReceipt" data-toggle="modal" data-target="#Fee_Receipt"  data-id="`+enroll+`">Fee Receipt</button>
                    </td>
                    <td><button class="btn btn-success btnClick" name="approve"  id=`+type+`_`+enroll+`>Approve</button></td>
                    <td>
        
                            <input type="text" class="form-control comment`+enroll+`"  id="comment`+type+``+enroll+`" name=`+type+`  placeholder="Unapprove Comment" required>
                            <button  value="Unapprove" class="btn btn-danger btnClick" name="disapprove"id=`+type+`_`+enroll+`>disapprove</button>
                        
                    </td>
                    </tr>`

                    $("#request_table").append(n);

                }
            }

            $(".openFeeReceipt").click(function() {
                var enroll = $(this).data("id");
                console.log(x[0].enrollment);
                for(var i=0;i<x.length;i++){
                    if(x[i].enrollment==enroll)
                        break;
                }
                    
                $('#fee_Receipt').attr("src","data:image/jpg;base64,"+x[i].fee_Receipt);
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