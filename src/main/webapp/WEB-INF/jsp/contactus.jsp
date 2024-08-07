<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" rel="stylesheet" id="fontawesome-css">
    <link rel="stylesheet" href="../css/nav.css">
    <link rel="stylesheet" href="../css/button.css">
    <link rel="stylesheet" href="../css/contactus.css">
    <title>Contact Us</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<form method="post" action="/contactus">
    <h1>Contact Us</h1>
    <div class="FormInputsRow">
        <div class="InputContainer">
            <label for="Subject">Subject *</label>
            <input type="text" id="Subject" required placeholder="Login Problem" name="msg_subject">
        </div>
    </div>
    <div class="FormInputsRow">
        <div class="InputContainer">
            <label for="Mail">E-Mail *</label>
            <input type="text" id="Mail" required placeholder="george@gmail.com" name="email">
        </div>
    </div>
    <div class="InputContainer">
        <label for="Message">Message *</label>
        <textarea id="Message" required placeholder="Write Your Message Here..." name="message"></textarea>
    </div>
    <button class="SubmitBTN" type="Submit">Send Message</button>
</form>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
</body>
</html>