var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; 
var yyyy = today.getFullYear();
if(dd<10) 
    dd='0'+dd;
if(mm<10) 
    mm='0'+mm;
today = dd+'/'+mm+'/'+yyyy;

function generateCertificate($name, $student) {

	console.log("s",$student);
	var $student_img = "data:image/png;base64,"+$student.student_photo

	var $contain = "";
	switch($name) {
		case "bonafide":
			$contain = generateBonafide($student); break;
		case "character":
			$contain = generateCharacter($student); break;
		case "conduct":
			$contain = generateConduct($student); break;
		case "rank":
			$contain = generateRank($student); break;
		default:
			return "Somthing Went Wrong!!";
	}

	$printContain = `
		<!DOCTYPE html>
		<html lang="en-US">
		<head>
			<meta charset="utf-8">
			<title>`+ $name.toUpperCase() +` Certificate</title>
			<style type="text/css">
				.certiContainer {
					margin-top: 8px;
					margin-left: auto;
					margin-right: auto;
					font-family: Calibri;
					font-size: 14pt;
				}
				.certiContainer u {
					text-transform: uppercase;
				}
				#cerificate-student-img{
		 		margin-top : 4px;
		        width:2.5cm;
		        heigh:2.5cm;
		        object-fit:cover;
				}
			</style>
		</head>
		<body>
			<div style="width:481.5pt;" class="certiContainer">
				<table style="margin-bottom:0pt; border-collapse:collapse; border-bottom: 0.75pt solid #000000">
					<tbody>
						<tr style="height:3.65cm;">
							<td style="width:3.81cm;">
								<img src="data:image/png;base64,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" alt="LDCE">
							</td>
							<td style="width:10.8cm; font-weight: bold; text-align:center; font-size:12pt; line-height: 1.5;">
								<span>L.D.College of Engineering,Ahmedabad-380015</span><br>
								<span>एल.डी.कोलेज ऑफ़ इंजीनियरिंग, अहमदाबाद – ३८००१५</span><br>
								<span>લા.દ.ઈજનેરી  મહાવિદ્યાલય,અમદાવાદ-૩૮૦૦૧૫</span><br>
								<span style="font-size: 14pt;">
									Phone:079-26306752 (Office), 26303190
								</span><br>
								<span style="font-size: 11pt;">
									Email: <a href="mailto:ldce-abad-dte@gujarat.gov.in">ldce-abad-dte@gujarat.gov.in</a> Website: www.ldce.ac.in
								</span>
							</td>
							<td style="width:2.38cm; vertical-align: top;">
								<img src=`+$student_img+` id="cerificate-student-img" alt="15 Years of Celebration The Mahatma">
							</td>
						</tr>
					</tbody>
				</table>
				<div style="margin-bottom: 10pt;">
					<span style="line-height:115%; font-size:14pt;">LDCE/STS/CERTI -`+ yyyy +`/</span>
					<span style="float: right; line-height:115%; font-size:14pt;">DATE: `+ today +`</span>
				</div>
				<p style="margin-top: 60pt; margin-bottom: 30pt; text-align: center;text-decoration: underline;"><span>`+ $name.toUpperCase() +` CERTIFICATE</span></p>`+
				
				$contain +

				`<table style="width: 100%; margin-top: 150pt;">
					<tr>
						<td style="width: 33.33%;"></td>
						<td style="width: 33.33%; text-align: center;">College Seal</td>
						<td style="width: 33.33%; text-align: right;">PRINCIPAL</td>
					</tr>
				</table>
			</div>
		</body>
		</html>`;
	
	return $printContain;
}

function generateBonafide($student) {
	$contain = `
		<p style="text-indent: 36pt; text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            This is to certify that <u>`+ $student.gender +". "+ $student.name +`</u> is a bonafide student of this institute studying in <u>`+ $student.course +" "+ $student.branch +`</u> (Sem- <u>`+ $student.sem +`</u> Regular - <u>`+ yyyy +`</u>) [Enrolment No. <u>`+ $student.enrollment +`</u>].
        </p>

        <p style="text-align: justify; line-height: 1.8;">
            The course is approved by ALL INDIA COUNCIL FOR TECHNICAL EDUCATION NEW DELHI and college is affiliated to the Gujarat Technological University. The Medium of instruction and examination is in  English.
		</p>`;
	return $contain;
}

function generateCharacter($student) {
	$contain = `
		<p style="text-indent: 36pt; text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
			This is to certify that <u>`+ $student.gender +". "+ $student.name +`</u>, student of this institute from <u>JUNE - `+ $student.addmissionYear +`</u> to <u>MAY - `+ $student.graduationYear +`</u>  in <u>`+ $student.course +" "+ $student.branch +`</u> [Enrolment No. <u>`+ $student.enrollment +`</u>].
		</p>

		<p style="text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
			The course is approved by ALL INDIA COUNCIL FOR TECHNICAL EDUCATION NEW DELHI and college is affiliated to the Gujarat Technological University. The Medium of instruction and examination is in  English.
		</p>

		<p style="text-indent: 36pt; text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
			He bears good moral character according to best of my knowledge.
		</p>`;
	return $contain;
}

function generateConduct($student) {
	$contain = `
		<p style="text-indent: 36pt; text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            This is to certify that <u>`+ $student.gender +". "+ $student.name +`</u> was student of this College from <u>JUNE - `+ $student.addmissionYear +`</u> to <u>MAY - `+ $student.graduationYear +`</u>  in <u>`+ $student.course +" "+ $student.branch +`</u> [Enrolment No. <u>`+ $student.enrollment +`</u>].
        </p>
        
        <p style="text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            He   has   completed his  study in  MAY – `+ $student.graduationYear +`  in Regular course with   CGPA  `+ $student.cgpa +`.
        </p>

        <p style="text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            The course is approved by ALL INDIA COUNCIL FOR TECHNICAL EDUCATION NEW DELHI and college is affiliated to the Gujarat Technological University. The Medium of instruction and examination is in  English.
        </p>

        <p style="text-indent: 36pt; text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            He bears good moral character according to best of my knowledge.
		</p>`;
	return $contain;
}

function generateRank($student) {
	$contain = `
		<p style="text-indent: 36pt; text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            This is to certify that <u>`+ $student.gender +". "+ $student.name +`</u> was student of this College from <u>JUNE - `+ $student.addmissionYear +`</u> to <u>MAY - `+ $student.graduationYear +`</u>  in <u>`+ $student.course +" "+ $student.branch +`</u> [Enrolment No. <u>`+ $student.enrollment +`</u>].
        </p>
        
        <p style="text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            He   has   completed his  study in  MAY – `+ $student.graduationYear +`  in Regular course.  His CGPA is `+ $student.cgpa +`. He got <u><b>`+ $student.rank +` rank</b></u> in  `+ $student.branch +`.
        </p>

        <p style="text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            The course is approved by ALL INDIA COUNCIL FOR TECHNICAL EDUCATION NEW DELHI and college is affiliated to the Gujarat Technological University. The Medium of instruction and examination is in  English.
        </p>

        <p style="text-indent: 36pt; text-align: justify; margin-bottom: 20pt; line-height: 1.8;">
            He bears good moral character according to best of my knowledge.
		</p>`;
	return $contain;
}


$("#printBtn").click(function() {
	
	printWindow=window.open("Certificate","PrintWindow","top=50,left=250,width=850,height=600");

	printWindow.document.write($("#viewCertificate").html());
    printWindow.document.close();
	printWindow.focus();
	printWindow.print();
	if( /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) )
		setTimeout(function () { printWindow.close(); }, 3000);
	else
		printWindow.close();

});