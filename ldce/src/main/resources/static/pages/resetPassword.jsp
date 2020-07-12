<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<title>L. D. College of Engineering</title> 
	
	<meta charset="utf-8"> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- favicon -->
	<link rel="shortcut icon" href="/images/favicon.ico">

    <!-- Bootstrap Core CSS -->
	<link rel="stylesheet" href="/plugins/bootstrap/css/bootstrap.min.css">
	
	<!-- font-awesome CSS -->
	<link rel="stylesheet" href="/plugins/font-awesome/css/font-awesome.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="/css/style.css">

</head> 

<body>

	<!-- navigation bar -->
    <div id="navigation-bar"></div>
	<!-- \.navigation bar -->

	<!-- contant -->
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-lg-4 mx-auto mx-auto">

				<!-- form -->
                <form method="POST" id="changePasswordForm" class="regForm" action="/student/newpassword" enctype="multipart/form-data">
                    
                    <h2 class="form-title text-center">Reset Password</h2>
                   
                    <div class="form-group">
                        <label for="new_password">New Password:</label>
                        <input class="form-control" type="password" name="new_password" id="new_password" maxlength="32" placeholder="New Password" required>
                    </div>

                    <div class="form-group">
                        <label for="confirm_password">Confirm Password:</label>
                        <input class="form-control" type="password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" maxlength="32" required>
                    </div>

                    <input type="hidden" value=${token} name="token" id="token" required>

                    <div class="form-group text-center">
                        <button type="submit" name="submit" class="btn btn-success">Save Changes</button>
                    </div>

                </form>   <!-- \.form -->
			</div>  <!-- \.col -->
		</div>  <!-- \.row -->
	</div>  <!-- \.container -->

</body>

    <!-- Jquery JavaScript -->
	<script src="/plugins/jquery/jquery.min.js"></script>
	<script src="/plugins/jquery/jquery.validate.min.js"></script> 
	<script src="/plugins/jquery/additional-methods.min.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="/plugins/bootstrap/js/popper.min.js"></script>
    
    <!-- Custom CSS -->
	<script src="/js/script.js"></script>
   
</html>