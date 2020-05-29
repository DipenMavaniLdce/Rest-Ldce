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
								<img src="data:image/png;base64,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" alt="15 Years of Celebration The Mahatma">
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