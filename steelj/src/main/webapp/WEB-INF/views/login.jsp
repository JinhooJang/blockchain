<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">

  <head>

    <!-- <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login</title>

    Bootstrap core CSS
    <link href="./resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    Custom fonts for this template
    <link href="./resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    Custom styles for this template
    <link href="./resources/css/sb-admin.css" rel="stylesheet"> -->
    <!-- header -->
    <jsp:include flush="false" page="./common/header.jsp"/>
	
  </head>

  <body class="bg-dark">

    <div class="container">
      <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
          <form method="get" action="login">
            <div class="form-group">
              <div class="form-label-group">
                <input type="text" name="address" id="address" class="form-control" placeholder="address" required="required" autofocus="autofocus">
                <label for="inputEmail">address</label>
              </div>
            </div>
            <!-- <div class="form-group">
              <div class="form-label-group">
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="required">
                <label for="inputPassword">Password</label>
              </div>
            </div> -->
            <!-- <div class="form-group">
              <div class="checkbox">
                <label>
                  <input type="checkbox" value="remember-me">
                  Remember Password
                </label>
              </div>
            </div> -->
            <button class="btn btn-primary btn-block">Login</button>
          </form>
          <!-- <div class="text-center">
            <a class="d-block small mt-3" href="register.html">Register an Account</a>
            <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
          </div> -->
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="./resources/vendor/jquery/jquery.min.js"></script>
    <script src="./resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="./resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  </body>

</html>
