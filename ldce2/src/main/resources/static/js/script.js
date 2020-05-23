// load Navigation bar, footer
$("#addNavigation").load("/pages/navigation.html");
$("#navigation-bar").load("/pages/navigation.html");
$("#addFooter").load("/pages/footer.html");

$("#admin-navigation-bar").load("/pages/AdminNavigation.html");
$("#student-navigation-bar").load("/pages/StudentNavigation.html");

// Form Control
// set page no
var currentformPage = 1;
    lastpage = 3;
showformPage(currentformPage);

// show form page
function showformPage(n) {

    $("#page-"+n).css("display","block");
    
    if (n == 1) {
        $("#prevBtn").css("display","none");
    } else {
        $("#prevBtn").css("display","inline");
    }
    if (n == lastpage) {
        $("#nextBtn").html("Submit");
        $("#step-"+n).addClass("finish");
    } else {
        $("#nextBtn").html("Next");
    }
    
    fixStepIndicator(n);
}

// set step indicator 
function fixStepIndicator(n) {
    
    for (i = 1; i <= lastpage; i++) {
        if($("#step-"+i).hasClass("active"))
            $("#step-"+i).removeClass("active");
    }
    
    $("#step-"+n).addClass("active");
}

// previous next button
function nextPrev(n) {

	if (n == 1 && !$("#regForm,#editProfileForm").valid()) return false;
	
	if ((currentformPage+n) > lastpage) {
    	console.log("iin submit")
    	AjexSubmit();
//        $("#regForm").submit();
        return false;
    }
	
    $("#page-"+currentformPage).css("display","none"); 
    currentformPage = currentformPage + n;
    
    $("#regForm").animate({scrollTop: 0}, 1000);
    showformPage(currentformPage);
}


// preview photo sign
$("#photo,#sign,#feeReceipt,#marksheet").change(function() {
    var target=$(this).attr('id');
    if (!this.files.length || !window.FileReader) return;
    $(this).blur().focus();
    if ( /^image/.test( this.files[0].type) ) {
        var reader = new FileReader();
        reader.readAsDataURL(this.files[0]);

        reader.onload = function (e) {
            $("#upload-"+target).attr("src",e.target.result);
            return true;
        };
    } else {
        $("#upload-"+target).attr("src","/images/"+target+".jpg");
        return false;
    }
});


// copy resident address to perment address
$("#copy_re").click(function(){
    if($(this). is(":checked")){
        $("#pr_add_l1").val($("#re_add_l1").val());
        $("#pr_add_l2").val($("#re_add_l2").val());
        $("#pr_add_city").val($("#re_add_city").val());
        $("#pr_add_state").val($("#re_add_state").val());
        $("#pr_add_pin_code").val($("#re_add_pin_code").val());
        if($("#re_add_country").val() !== null)
            $("#pr_add_country").val($("#re_add_country").val());
    }
});


// validator add method
$.validator.addMethod( "regex", function(value, element, param) {
    var re = new RegExp(param);
    return this.optional(element) || re.test(value);
    },  "Please check your input."
);

var nameRule = {
        required: true,
        regex: /^[A-Za-z. ]+$/
    },
    addLineRule = {
        required:true,
        regex: /^[a-zA-Z0-9-.,\/] ?([a-zA-Z0-9-.,\/]|[a-zA-Z0-9-.,\/] )*[a-zA-Z0-9-.,\/]$/,
    },
    addCityRule = {
        required: true,
        regex:  /^[a-zA-z] ?([a-zA-z]|[a-zA-z] )*[a-zA-z]$/,
    },
    addPinRule = {
        required:true,
        regex: /^[1-9][0-9]{5}$/,
    };

// student registration form validation
$("#regForm,#editProfileForm").validate({

    invalidHandler: function(form, validator) {
        $('html, body').animate({
            scrollTop: $(validator.errorList[0].element).offset().top -100
        }, 1000);
        $(validator.errorList[0].element).focus();
    },
    
    rules: {
        first_name: nameRule,
        middle_name: nameRule,
        last_name: nameRule,
        email: {
            required: true,
            regex: /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})$/,
        },
        contact: {
            required: true,
            regex: /^[5-9][0-9]{9}$/,
        },
        password: {
            regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])/,
            minlength:8,
            maxlength:32,
        },
        confirm_password : {
            required: true,
            equalTo: '#password',
        },
        religion: nameRule,
        enrollment : {
            maxlength:12,
            minlength:12,
            regex: /^[1-2][0-9]028[0-9]{7}$/,
        },
        photo: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        },
        sign: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        },
        father_name: nameRule,
        mother_name: nameRule,
        aadhar: {
            required:true,
            maxlength:12,
            minlength:12,
        },
        re_add_l1: addLineRule,
        re_add_l2: addLineRule,
        re_add_city: addCityRule,
        re_add_state: addCityRule,
        re_add_pin_code: addPinRule,
        pr_add_l1: addLineRule,
        pr_add_l2: addLineRule,
        pr_add_city: addCityRule,
        pr_add_state: addCityRule,
        pr_add_pin_code: addPinRule,
        off_add_l1: addLineRule,
        off_add_l2: addLineRule,
        off_add_city: addCityRule,
        off_add_state: addCityRule,
        off_add_pin_code: addPinRule,
        hsc_pr: {
            required:true,
            regex: /^[0-9]{1,2}\.[0-9]{1,2}$/,
        },
        ssc_pr: {
            required:true,
            regex: /^[0-9]{1,2}\.[0-9]{1,2}$/,
        },
        father_occupation: nameRule,
        mother_occupation: nameRule,
        family_income: {
            required: true,
            regex: /^[0-9]+$/,
        }
    },
    messages: {
        first_name: "Please specify your valid first name.",
        middle_name: "Please specify your valid middle name.",
        last_name: "Please specify your valid last name.",
        father_name: "Please specify your valid father name.",
        mother_name: "Please specify your valid mother name.",
        email: "Please specify a valid email address.",
        contact: "Please specify a valid Mobile Number.",
        password : `<div id="repassword">
                        <span>Password must contain the following:</span>
                        <p>A <b>lowercase</b> letter.</p>
                        <p>A <b>UPPERCASE</b> letter.</p>
                        <p>A <b>number (0-9)</b>.</p>
                        <p>A <b>special (!@#$%^&*) characters</b>.</p>
                        <p>Password length between <b>8-32 characters</b>.</p>
                    </div>`,
        confirm_password: "Password don't match.",
        gender: "Please select your gender.",
        caste: "Please select your caste.",
        religion: "Please specify your valid religion.",
        addmission_year: "Please select your Addmission year.",
        semester: "Please select your Semester.",
        enrollment: "Please enter valid Enrollment No.",
        branch: "Please select your Branch.",
        course: "Please select your Course.",
        photo: {
            required: "Please Upload your photo.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        },
        sign: {
            required: "Please Upload your sign.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        },
        aadhar: "Please enter valid Aadhar number.",
        date_of_birth: "Pleasr enter Date of Birth.",
        blood_group: "Please select your blood group.",
        ssc_year: "Please select your SSC year.",
        hsc_year: "Please select your HSC year.",
        ssc_pr: "Please enter valid SSC PR.",
        hsc_pr: "Please enter valid HSC PR.",
        father_occupation:"Please specify your valid father occupation",
        mother_occupation:"Please specify your valid mother occupation",
        family_income: "Please enter your valid family income",
        re_add_l1: "Enter valid address line 1",
        re_add_l2: "Enter valid address line 2",
        re_add_city: "Enter valid city name",
        re_add_state: "Enter valid state name",
        re_add_pin_code: "Check your Pin-Code",
        re_add_country: "Select country",
        pr_add_l1: "Enter valid address line 1",
        pr_add_l2: "Enter valid address line 2",
        pr_add_city: "Enter valid city name",
        pr_add_state: "Enter valid state name",
        pr_add_pin_code: "Check your Pin-Code",
        pr_add_country: "Select country",
        off_add_l1: "Enter valid address line 1",
        off_add_l2: "Enter valid address line 2",
        off_add_city: "Enter valid city name",
        off_add_state: "Enter valid state name",
        off_add_pin_code: "Check your Pin-Code",
        off_add_country: "Select country",
    },
});

// faculty reg Form validation
$("#facultyRegForm").validate({
    invalidHandler: function(form, validator) {
        $('html, body').animate({
            scrollTop: $(validator.errorList[0].element).offset().top -100
        }, 1000);
        $(validator.errorList[0].element).focus();
    },
    rules: {
        first_name: nameRule,
        middle_name: nameRule,
        last_name: nameRule,
        email: {
            regex: /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})$/,
        },
        contact: {
            regex: /^[5-9][0-9]{9}$/,
        },
        password: {
            regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])/,
            minlength:8,
            maxlength:32,
        },
        confirm_password : {
            equalTo: '#password',
        },
        photo: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        },
        sign: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        },
        re_add_l1: addLineRule,
        re_add_l2: addLineRule,
        re_add_city: addCityRule,
        re_add_state: addCityRule,
        re_add_pin_code: addPinRule,
    },
    messages: {
        first_name: "Please specify your valid first name.",
        middle_name: "Please specify your valid middle name.",
        last_name: "Please specify your valid last name.",
        email: "Please specify a valid email address.",
        contact: "Please specify a valid Mobile Number.",
        password : `<div id="repassword">
                        <span>Password must contain the following:</span>
                        <p>A <b>lowercase</b> letter.</p>
                        <p>A <b>UPPERCASE</b> letter.</p>
                        <p>A <b>number (0-9)</b>.</p>
                        <p>A <b>special (!@#$%^&*) characters</b>.</p>
                        <p>Password length between <b>8-32 characters</b>.</p>
                    </div>`,
        confirm_password: "Password don't match.",
        branch: "Please select your Branch.",
        branch_year: "Please select your Beanch year.",
        faculty_id: "Please enter your faculty id.",
        designation: "Please enter your designation",
        gender: "Please select your gender.",
        photo: {
            required: "Please Upload your photo.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        },
        sign: {
            required: "Please Upload your sign.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        },
        date_of_birth: "Pleasr enter Date of Birth.",
        blood_group: "Please select your blood group.",
        re_add_l1: "Enter valid address line 1",
        re_add_l2: "Enter valid address line 2",
        re_add_city: "Enter valid city name",
        re_add_state: "Enter valid state name",
        re_add_pin_code: "Check your Pin-Code",
        role: "Please Select Role.",
    }
});


// login form validation
$("#loginForm").validate({

    invalidHandler: function(form, validator) {
        $(validator.errorList[0].element).focus();
    },
    
    rules: {
        username: {
            required:true,
            regex: /(^[1-2][0-9]028[0-9]{7}$)||(^(([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4}))$)/,
        }
        
    },
    messages: {
        type: "Please select role.",
        username: "Please enter valid email address.",
        password: "Please enter password.",
    }
});

// forget password form validation
$("#forgetPassForm").validate({
    errorLabelContainer: '.errorTxt',
    rules: {
        email: {
            required: true,
            regex: /^(([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4}))$/,
        },
    },
    messages: {
        email: "Please enter valid email address.",
    },
});


// change password form validation
$("#changePasswordForm").validate({
    rules: {
        new_password: {
            regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])/,
            minlength:8,
            maxlength:32,
        },
        confirm_password : {
            equalTo: '#password',
        },
    },
    messages: {
        current_password: "Please enter current password",
        new_password : `<div id="repassword">
                        <span>Password must contain the following:</span>
                        <p>A <b>lowercase</b> letter.</p>
                        <p>A <b>UPPERCASE</b> letter.</p>
                        <p>A <b>number (0-9)</b>.</p>
                        <p>A <b>special (!@#$%^&*) characters</b>.</p>
                        <p>Password length between <b>8-32 characters</b>.</p>
                    </div>`,
        confirm_password: "Password don't match.",
    },
});

// change Photo form validation
$("#changePhotoForm").validate({
    rules: {
        photo: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        }
    },
    messages: {
        photo: {
            required: "Please Upload your photo.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        }
    },
});

// change Sign form validation
$("#changeSignForm").validate({
    rules: {
        sign: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        }
    },
    messages: {
        sign: {
            required: "Please Upload your sign.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        }
    },
});


// Apply Certificate Form Validation
$("#applyCertiForm").validate({
    rules: {
        feeReceipt: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        },
        marksheet: {
            accept: "jpg,png,jpeg",
            maxsize : 512000,
        },
        cgpa: {
            required:true,
            regex: /^[0-9]{1,2}\.[0-9]{1,2}$/,
        },
        rank: {
            regex: /^[a-zA-Z]+$/,
        },
    },
    messages: {
        feeReceipt: {
            required: "Please Upload Fee receipt.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        },
        marksheet: {
            required: "Please Upload your final year marksheet.",
            accept: "Only jpg,jpeg,png file type allow.",
            maxsize: "File size must not exceed 500 KB.",
        },
        cgpa: "Please enter valid CGPA.",
        rank: "Please enter valid Rank.",
    },
});