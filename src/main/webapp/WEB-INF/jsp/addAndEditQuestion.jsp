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
    <link rel="stylesheet" href="../css/addQuestionForm.css">
    <title>Home</title>

</head>

<body>

<jsp:include page="navbar.jsp" />

<c:if test="${edit == true}">
<form id="question-form" action="/editQuestion?questionID=${question.question_id}" method="post">
</c:if>
<c:if test="${edit != true}">
<form id="question-form" action="/addQuestion" method="post">
</c:if>
    <fieldset>
        <label id="subject-label">Subject<input id="subject" name="subject" type="text" placeholder="Enter your Subject" ${edit? "value=" : ""}${edit? question.question_subject : ""} required /></label>
        <label id="description-label">Description
            <c:if test="${edit == true}">
                <input id="description" name="description" type="text" placeholder="Enter your Description" value="${question.question_description}" required />
            </c:if>
            <c:if test="${edit != true}">
                <input id="description" name="description" type="text" placeholder="Enter your Description" required />
            </c:if>
        </label>
        <label> Category
            <select id="dropdown" name="category">
                <option value="OSINT" ${question.category_id == 1? 'selected="selected"' : ''}>OSINT</option>
                <option value="Password Cracking" ${question.category_id == 2? 'selected="selected"' : ''}>Password Cracking</option>
                <option value="Cryptographic" ${question.category_id == 3? 'selected="selected"' : ''}>Cryptographic</option>
            </select>
        </label>
    </fieldset>
    <fieldset>
        <c:choose>
            <c:when test="${edit == true}">
                <c:forEach items="${questionOptions}" var="option" varStatus="loop">
                    <label>
                        <input type="radio" class="inline" name="attribute" value="${loop.index+1}" ${question.answer == option.content? "checked" : ""} />Option ${loop.index+1}:  <input type="text" class="inline" name="option${loop.index+1}" value="${option.content}" />
                        <input type="text" class="inline" name="option${loop.index+1}_id" value="${option.option_id}" hidden="hidden" />
                    </label>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <label><input type="radio" class="inline" name="attribute" value="1" checked />Option 1:  <input type="text" class="inline" name="option1" /></label>
                <label><input type="radio" class="inline" name="attribute" value="2" />Option 2:  <input type="text" class="inline" name="option2" /></label>
                <label><input type="radio" class="inline" name="attribute" value="3" />Option 3:  <input type="text" class="inline" name="option3" /></label>
                <label><input type="radio" class="inline" name="attribute" value="4" />Option 4:  <input type="text" class="inline" name="option4" /></label>
            </c:otherwise>
        </c:choose>
    </fieldset>

    <fieldset>
        <input type="submit" value="Submit" id="submit" style="margin-top: 10px;"/>
    </fieldset>
</form>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
</body>

</html>
