<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="modal fade" id="change_password" tabindex="-1" role="dialog" aria-labelledby="change_password_title">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="change_password_title">Change Password</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>  <!--/.modal-header-->

            <div class="modal-body">
                <form method="POST" id="changePasswordForm" action="../student/newpassword" enctype="multipart/form-data">
                   
                    <div class="form-group">
                        <label for="new_password">New Password:</label>
                        <input class="form-control" type="password" name="new_password" id="new_password" maxlength="32" required>
                    </div>

                    <div class="form-group">
                        <label for="confirm_password">Confirm Password:</label>
                        <input class="form-control" type="password" name="confirm_password" id="confirm_password" maxlength="32" required>
                    </div>
                    <div class="form-group" >
                        <label for="token">Token:</label>
                        <input class="form-control" type="text" value=${token} name="token" id="token" maxlength="32" required>
                    </div>


                    <div class="modal-footer">
                        <button type="submit" name="submit" class="btn btn-success">Save Changes</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
</body>
</html>