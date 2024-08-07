<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" rel="stylesheet" id="fontawesome-css">
    <link rel="stylesheet" href="../css/button.css">
    <link rel="stylesheet" href="../css/nav.css">
    <link rel="stylesheet" href="../css/table.css">
    <title>Home</title>

</head>

<body>

<jsp:include page="navbar.jsp" />
<h1 style="text-align: center;">Contact Us List</h1>

<div>
    <table class="styled-table" id="userTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Subject</th>
            <th>Email</th>
            <th>Message</th>
            <th>Submit Time</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty contactUsAll}">
            <c:forEach items="${contactUsAll}" var="contactusMsg">
                <tr>
                    <td>${contactusMsg.contactus_id}</td>
                    <td>${contactusMsg.msg_subject}</td>
                    <td>${contactusMsg.email}</td>
                    <td>${contactusMsg.message}</td>
                    <td>${contactusMsg.submit_time}</td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="pagination" style="display: flex; align-items: center; justify-content: center; margin-top: 20px;">
        <button onclick="prevPage()" style="background: #4c49ea; color: white; margin-right: 5px; ">Previous</button>
        <span id="pageInfo"></span>
        <button onclick="nextPage()" style="background: #4c49ea; color: white; margin-left: 5px;">Next</button>
    </div>
</div>

<script src="../js/pageChange.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
</body>

</html>
