<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <title>Login Page</title>
</head>

<body>

<%--<jsp:include page="navbar.jsp" />--%>

<div class="container" id="container">
    <div class="form-container sign-up">
        <form method="post" action="/register">
            <h1>Create Account</h1>
            <div class="social-icons">
                <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                <a href="#" class="icon"><i class="fa-brands fa-github"></i></a>
                <a href="#" class="icon"><i class="fa-brands fa-linkedin-in"></i></a>
            </div>
            <span>or use your email for registeration</span>
            <input type="text" placeholder="User Name" name="register_username">
            <input type="text" placeholder="First Name" name="register_firstname">
            <input type="text" placeholder="Last Name" name="register_lastname">
            <input type="email" placeholder="Email" name="register_email">
            <input type="password" placeholder="Password" name="register_passwd">
            <button>Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in">
        <form method="post" action="/login">
            <h1>Sign In</h1>
            <div class="social-icons">
                <a href="#" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
                <a href="#" class="icon"><i class="fa-brands fa-facebook-f"></i></a>
                <a href="#" class="icon"><i class="fa-brands fa-github"></i></a>
                <a href="#" class="icon"><i class="fa-brands fa-linkedin-in"></i></a>
            </div>
            <span>or use your email password</span>
            <input type="email" placeholder="Email" name="login_email">
            <input type="password" placeholder="Password" name="login_passwd">
            <c:if test="${not empty error_msg}">
                <p style="color:red">${error_msg}</p>
            </c:if>
            <a href="#">Forget Your Password?</a>
            <button>Sign In</button>
            <a href="/contactus">Contact Us</a>
        </form>
    </div>
    <div class="toggle-container">
        <div class="toggle">
            <div class="toggle-panel toggle-left">
                <h1>Welcome Back!</h1>
                <p>Enter your personal details to use all of site features</p>
                <button class="hidden" id="login">Sign In</button>
            </div>
            <div class="toggle-panel toggle-right">
                <h1>Hello, Friend!</h1>
                <p>Register with your personal details to use all of site features</p>
                <button class="hidden" id="register">Sign Up</button>
            </div>
        </div>
    </div>
</div>

<script src="../js/script.js"></script>
</body>

</html>
