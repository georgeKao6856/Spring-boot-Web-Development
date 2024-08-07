<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" rel="stylesheet" id="fontawesome-css">
    <link rel="stylesheet" href="../css/question_result.css">
    <link rel="stylesheet" href="../css/button.css">
    <link rel="stylesheet" href="../css/nav.css">
    <link rel="stylesheet" href="../css/table.css">
    <title>QuizResult</title>

</head>

<body>

<jsp:include page="navbar.jsp" />

<h1 style="text-align: center;">${quiz_name}</h1>
<h3 style="color:red; text-align: center;">Score: ${score}</h3>
<h5 style="text-align: center; ${pass == "Pass" ? "color:green;": "color:red"}">${pass}</h5>
<h5 style="text-align: center;">Start time: ${start_time}</h5>
<h5 style="text-align: center;">End time: ${end_time}</h5>

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
                            <c:forEach var="array" items="${checkedSelectedResult}">
                                <c:if test="${question.question_id == array[0]}">
                                    <c:choose>
                                        <c:when test="${array[1] == array[2]}">
                                            <c:choose>
                                                <c:when test="${option.content == array[2]}">
                                                    <div id="radio" style="margin-left: 36px !important;">
                                                        <label>
                                                            <input type="radio" name="question${question.question_id}" value="${option.content}" disabled="disabled">
                                                            <span class="round button" style="background: green">${option.content}</span>
                                                        </label>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div id="radio" style="margin-left: 36px !important;">
                                                        <label>
                                                            <input type="radio" name="question${question.question_id}" value="${option.content}" disabled="disabled">
                                                            <span class="round button">${option.content}</span>
                                                        </label>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${option.content == array[2]}">
                                                    <div id="radio" style="margin-left: 36px !important;">
                                                        <label>
                                                            <input type="radio" name="question${question.question_id}" value="${option.content}" disabled="disabled">
                                                            <span class="round button" style="background: red">${option.content}</span>
                                                        </label>
                                                    </div>
                                                </c:when>
                                                <c:when test="${option.content == array[1]}">
                                                    <div id="radio" style="margin-left: 36px !important;">
                                                        <label>
                                                            <input type="radio" name="question${question.question_id}" value="${option.content}" disabled="disabled">
                                                            <span class="round button" style="background: green">${option.content}</span>
                                                        </label>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div id="radio" style="margin-left: 36px !important;">
                                                        <label>
                                                            <input type="radio" name="question${question.question_id}" value="${option.content}" disabled="disabled">
                                                            <span class="round button">${option.content}</span>
                                                        </label>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<div style="display: flex; align-items: center; justify-content: center; margin-top: 10px">
    <a href="/home" class="button">Home</a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
</body>

</html>
