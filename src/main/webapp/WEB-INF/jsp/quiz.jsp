<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" rel="stylesheet" id="fontawesome-css">
    <link rel="stylesheet" href="../css/question.css">
    <link rel="stylesheet" href="../css/button.css">
    <link rel="stylesheet" href="../css/nav.css">
    <link rel="stylesheet" href="../css/table.css">
    <title>${param["quiz_name"]}</title>

</head>

<body>

<c:if test="${not empty OngoingQuiz}">
    <script>alert("Please complete the previous quiz first!")</script>
</c:if>

<jsp:include page="navbar.jsp" />

<h1 style="text-align: center;">${quiz_name}</h1>
<form method="post" action="/quiz">
    <c:forEach  items="${questions}" var="entry" varStatus="loop">
        <c:set var="question" value="${entry.key}" />
        <c:set var="options" value="${entry.value}" />
        <div class="container mt-5">
            <div class="d-flex justify-content-center row">
                <div class="col-md-10 col-lg-10">
                    <div class="border">
                        <div class="question bg-white p-3 border-bottom">
                            <div class="d-flex flex-row justify-content-between align-items-center mcq">
                                <h4>${question.question_subject}</h4>
                            </div>
                        </div>
                        <div class="question bg-white p-3 border-bottom">
                            <div class="d-flex flex-row align-items-center question-title">
                                <h3 class="text-danger">Q.</h3>
                                <h5 class="mt-1 ml-2">${question.question_description}</h5>
                            </div>
                            <c:forEach var="option" items="${options}">
                                <div id="radio" style="margin-left: 36px !important;">
                                    <label>
                                        <input type="radio" name="question${question.question_id}" value="${option.content}">
                                        <span class="round button">${option.content}</span>
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    <div style="display: flex; align-items: center; justify-content: center; margin-top: 10px">
        <button type="Submit" class="button">Submit</button>
    </div>
</form>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
</body>

</html>
