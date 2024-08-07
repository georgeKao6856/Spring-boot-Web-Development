<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" rel="stylesheet" id="fontawesome-css">
    <link rel="stylesheet" href="../css/card.css">
    <link rel="stylesheet" href="../css/button.css">
    <link rel="stylesheet" href="../css/nav.css">
    <link rel="stylesheet" href="../css/table.css">
    <title>Home</title>

</head>

<body>

<jsp:include page="navbar.jsp" />

<c:forEach items="${categories}" var="choice_c" varStatus="loop">
    <div class="container">
        <h1>${choice_c.category_name}</h1>
    <c:forEach items="${quizzes}" var="choice_q" varStatus="loop">
        <c:if test = "${choice_c.category_id == choice_q.category.category_id}">
        <div class="ag-format-container">
            <div class="ag-courses_box">
                <div class="ag-courses_item">
                    <a href="/quiz?quiz_id=${choice_q.quiz_id}&quiz_name=${choice_q.quiz_name}" class="ag-courses-item_link">
                        <div class="ag-courses-item_bg"></div>

                        <div class="ag-courses-item_title">
                            ${choice_q.quiz_name}
                        </div>
                    </a>
                </div>
            </div>
        </div>
        </c:if>
    </c:forEach>
    </div>
</c:forEach>
<div>
    <h1 style="text-align: center;">History Result</h1>
</div>
<div>
    <table class="styled-table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Date</th>
            <th>Link</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty historyQuizzes}">
            <c:forEach items="${historyQuizzes}" var="choice" varStatus="loop">
                <tr>
                    <td>${choice.quiz_name}</td>
                    <td>${choice.quiz_date}</td>
                    <td><a href="/quiz-result/${choice.historyquiz_id}">Result</a></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
</body>

</html>
