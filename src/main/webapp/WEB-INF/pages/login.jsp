<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elvin
  Date: 12/10/2019
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript">
        history.pushState(null, null, 'login.jsp');
        window.addEventListener('popstate', function (event) {
            history.pushState(null, null, 'login.jsp');
        });

    </script>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="./resources/logun/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="./resources/logun/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="./resources/logun/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="./resources/logun/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="./resources/logun/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="./resources/logun/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="./resources/logun/css/util.css">
    <link rel="stylesheet" type="text/css" href="./resources/logun/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="./resources/logun/images/img-01.png" alt="IMG">
            </div>

            <form action="loginU" method="post">
                <span class="login100-form-title">
						Member Login
					</span>
                <div class="wrap-input100 validate-input">
                    <input class="input100" type="text" name="username" placeholder="Email">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                </div>

                <c:if test="${not empty success}">
                    <label style="color: green">${success}</label>
                </c:if>
                <c:if test="${not empty invalid}">
                    <label style="color: red">${invalid}</label>
                </c:if>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit">Login</button>
                </div>
                <div class="text-center p-t-12">
						<span class="txt1">
							Forgot
						</span>
                    <a class="txt2" href="forgotPassword">
                        Username / Password?
                    </a>
                </div>
                <div class="text-center p-t-136">
                    <a class="txt2" href="#">
                        Create your Account
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>




<!--===============================================================================================-->
<script src="./resources/logun/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="./resources/logun/vendor/bootstrap/js/popper.js"></script>
<script src="./resources/logun/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="./resources/logun/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="./resources/logun/vendor/tilt/tilt.jquery.min.js"></script>
<script >
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="./resources/logun/js/main.js"></script>

</body>
</html>
