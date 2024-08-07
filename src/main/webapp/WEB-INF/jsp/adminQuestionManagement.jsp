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
    <link rel="stylesheet" href="../css/dropdown.css">
    <title>Home</title>

</head>

<body>

<jsp:include page="navbar.jsp" />
<h1 style="text-align: center;">Question List</h1>

<div class="container">
    <form name="form" class="form" method="post">
        <h3 style="margin-right: 10px;">Category:  </h3>
        <div class="selectbox selectbox--unselect" data-option="">
            <div class="selectbox__displayWord">
                ${categoryShow}
            </div>
            <div class="option-container">
                <div class="option-container__option">
                    <input type="radio" class="option__radio" id="1100" name="category">
                    <label class="option__label" for="1100" data-value="All">All</label>
                </div>
                <div class="option-container__option">
                    <input type="radio" class="option__radio" id="1200" name="category">
                    <label class="option__label" for="1200" data-value="OSINT">OSINT</label>
                </div>
                <div class="option-container__option">
                    <input type="radio" class="option__radio" id="1300" name="category">
                    <label class="option__label" for="1300" data-value="Password Cracking">Password Cracking</label>
                </div>
                <div class="option-container__option">
                    <input type="radio" class="option__radio" id="1400" name="category">
                    <label class="option__label" for="1400" data-value="Cryptographic">Cryptographic</label>
                </div>
            </div>
        </div>
        <button class="form__submit-button" type="button">Search</button>
    </form>

    <a href="/addQuestion" class="button" style="display: flex; align-items: center; justify-content: center; margin: 10px; width: 200px;">Add Question</a>
</div>

<div>
    <table class="styled-table" id="userTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Subject</th>
            <th>Description</th>
            <th>Category</th>
            <th>Edit</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty questionsList}">
            <c:forEach items="${questionsList}" var="question">
                <tr>
                    <td>${question.question_id}</td>
                    <td>${question.question_subject}</td>
                    <td>${question.question_description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${question.category_id == 1}">
                                OSINT
                            </c:when>
                            <c:when test="${question.category_id == 2}">
                                Password Cracking
                            </c:when>
                            <c:when test="${question.category_id == 3}">
                                Cryptography
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <a href="/editQuestion?questionID=${question.question_id}" class="button">Edit</a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${question.active == true}">
                                <form action="/updateQuestionStatus?questionID=${question.question_id}" method="post">
                                    <button type="submit" style="background: green">
                                        Active
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="/updateQuestionStatus?questionID=${question.question_id}" method="post">
                                    <button type="submit" style="background: red">
                                        Suspended
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>

                    </td>
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
<script>
    const selectbox = document.querySelector(".selectbox");
    const selectboxDisplay = document.querySelector(".selectbox__displayWord");
    const submitbtn = document.querySelector(".form__submit-button");
    const form = document.forms['form'];

    const optionList = document.querySelectorAll(".option-container__option");

    selectboxDisplay.addEventListener("click", (e) => {
        e.stopPropagation();
        selectbox.classList.toggle("selectbox--active");
    });

    function shakeBox() {
        selectbox.classList.add("selectbox--shake");
        setTimeout(() => {
            selectbox.classList.remove("selectbox--shake");
        }, 300);
    }

    optionList.forEach((option, index) => {
        option.addEventListener("click", (e) => {
            e.stopPropagation();

            let label = option.querySelector("label");
            selectboxDisplay.innerHTML = label.innerHTML;
            selectbox.setAttribute("data-option", label.getAttribute("data-value"));
            selectbox.classList.remove("selectbox--active", "selectbox--unselect");
        });
    });

    submitbtn.addEventListener("click", (e) => {
        const selectedOption = selectbox.getAttribute("data-option");
        if (selectbox.classList.contains("selectbox--unselect")) {
            shakeBox();
            e.preventDefault();
        } else {
            form.action = "/getQuestionListByCategory?category=" + selectedOption;
            form.submit();
        }
    });


    window.addEventListener("click", () => {
        selectbox.classList.remove("selectbox--active");
    });

</script>

<script src="../js/pageChange.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd" crossorigin="anonymous"></script>
</body>

</html>
