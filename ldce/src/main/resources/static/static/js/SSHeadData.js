$("#bth-search").click(function() {
	$("#bth-search").html("<span class='spinner-border spinner-border-sm'></span> Loading..");
	$("#bth-search").attr("disabled","disabled");
	$('.datarow').remove();
	
	var btnId=this;
	var c=$(".btnClick");
	var comment_value = $("#comment"+this.id).val();
	var dataString1={enrollment:this.id,status:this.name,comment:comment_value} ;
	
	console.log(dataString1);
	
	dataString = "/admin/ssheaddata?caste="+$("#caste").val()+"&addmission_year="+$("#addmission_year").val()+"&course="+$("#course").val()+"&gender="+$("#gender").val()+"&semester="+$("#semester").val()+"&branch="+$("#branch").val();
        	   
	console.log(dataString);
        	    
    $.ajax({
		type: "get",
		url: dataString,
		success: function (x) {
			
			console.log(x);
			showTableData(x); 
		
			function showProfileData(x) {
				console.log("in show profile")
				for(value in x) {
					if (value == "info")
						showProfileData(x.info);
					else if (value == "guardian") 
						showProfileData(x.guardian);
					else if (value == "request") {
					
						for (var i = 0 ; i<x.request.length;i++)
							{ console.log(x.request[i]);
							if(x.request[i].type=="bonafide")
							{if(x.request[i].live){
							var status1 =x.request[i].status1==1?'Approve':(x.request[i].status1==0 ?'Pending':'Disapproved')
							var status2 =x.request[i].status2==1?'Approve':(x.request[i].status2==0 ?'Pending':'Disapproved')
							var status3 =x.request[i].status3==1?'Approve':(x.request[i].status3==0 ?'Pending':'Disapproved')
								$('#vb1').html(status1);
								$('#vb2').html(status2);
								$('#vb3').html(status3);}
							else{
								$('#vb1').html('-');
								$('#vb2').html('-');
								$('#vb3').html('-');
						}
							}
							if(x.request[i].type=="character")
							{if(x.request[i].live){
							var status1 =x.request[i].status1==1?'Approve':(x.request[i].status1==0 ?'Pending':'Disapproved')
							var status2 =x.request[i].status2==1?'Approve':(x.request[i].status2==0 ?'Pending':'Disapproved')
							var status3 =x.request[i].status3==1?'Approve':(x.request[i].status3==0 ?'Pending':'Disapproved')
								$('#vc1').html(status1);
								$('#vc2').html(status2);
								$('#vc3').html(status3);}
							else{
									$('#vc1').html('-');
									$('#vc2').html('-');
									$('#vc3').html('-');
							}
							}
							}
						
						
					}
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

			$(".openStudentModal").click(function() {
				console.log("ddidi");
				var enroll = $(this).data("id");
				console.log(x[0].email);
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
			
			
			$("#bth-search").html("Search");
			$("#bth-search").removeAttr("disabled");
			
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


	function showTableData(x) {
		
		
		
		
		
		
		
		for(var i=0;i<x.length;i++) {
		
			var enroll= x[i].enrollment 
				name = x[i].first_name + " " + x[i].middle_name + " " + x[i].last_name,
				sem = x[i].semester,
				branch = x[i].branch;
				gmail=x[i].email;
				enrollment = x[i].enrollment
		var n =
			`<tr class="datarow">
			<td>`+(i+1)+`</td>
			<td>`+enrollment+`</td>
			<td>`+name+`</td>
			<td>`+sem+`</td>
		
			<td>`+gmail+`</td>
			<td>
				<button class="btn btn-info openStudentModal" data-toggle="modal" data-target="#view_profile"  data-id="`+enroll+`" onclick="">View Profile</button>
			</td>
			
			</tr>`

			$("#request_table").append(n);
		
		

		}
	
	}


