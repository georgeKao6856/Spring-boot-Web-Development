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

    <style>
        .container {
            display: flex;
            gap: 40px; /* 设置两个下拉式选单之间的间距 */
            justify-content: center; /* 水平居中对齐 */
            padding: 20px;
        }
        .form-inline {
            display: flex;
            align-items: center;
            gap: 10px;
            background: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        label {
            font-weight: bold;
        }
        select, input[type="submit"] {
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        select {
            background: #f9f9f9;
            cursor: pointer;
        }
        input[type="submit"] {
            background: #007bff;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background: #0056b3;
        }
    </style>

</head>

<body>

<jsp:include page="navbar.jsp" />
<h1 style="text-align: center;">Quiz Result List</h1>

<div class="container">
    <form class="form-inline" method="get" action="/admin_quiz_result_management">
        <label for="username">Username</label>
        <select name="filterUsername" id="username">
            <option value="All" ${filterUsernameSubmit=="ALL"? "selected": ""}>All</option>
            <c:forEach items="${usernames}" var="username">
                <option value="${username}" ${filterUsernameSubmit==username? "selected":""}>${username}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="orderBy" value="quiz_end_time">
        <input type="hidden" name="filterCategory" value="All">
        <input type="submit" value="Search" />
    </form>

    <form action="/admin_quiz_result_management" class="form-inline" method="get">
        <label for="category">Category</label>
        <select name="filterCategory" id="category">
            <option value="All" ${filterCategorySubmit=="ALL"? "selected": ""}>All</option>
            <option value="OSINT" ${filterCategorySubmit=="OSINT"? "selected": ""}>OSINT</option>
            <option value="Password Cracking" ${filterCategorySubmit=="Password Cracking"? "selected": ""}>Password Cracking</option>
            <option value="Cryptographic" ${filterCategorySubmit=="Cryptographic"? "selected": ""}>Cryptographic</option>
        </select>
        <input type="hidden" name="orderBy" value="quiz_end_time">
        <input type="hidden" name="filterUsername" value="All">
        <input type="submit" value="Search" />
    </form>
</div>

<div>
    <table class="styled-table" id="userTable">
        <thead>
        <tr>
            <th><a href="/admin_quiz_result_management?orderBy=historyquiz_id&filterCategory=${filterCategorySubmit}&filterUsername=${filterUsernameSubmit}" style="color: white">ID ${sortBy == "ID" ? "↓" : ""}</a></th>
            <th><a href="/admin_quiz_result_management?orderBy=quiz_name&filterCategory=${filterCategorySubmit}&filterUsername=${filterUsernameSubmit}" style="color: white">Quiz Name ${sortBy == "Quiz Name" ? "↓" : ""}</a></th>
            <th><a href="/admin_quiz_result_management?orderBy=category_name&filterCategory=${filterCategorySubmit}&filterUsername=${filterUsernameSubmit}" style="color: white">Category ${sortBy == "Category" ? "↓" : ""}</a></th>
            <th><a href="/admin_quiz_result_management?orderBy=username&filterCategory=${filterCategorySubmit}&filterUsername=${filterUsernameSubmit}" style="color: white">Username ${sortBy == "Username" ? "↓" : ""}</a></th>
            <th><a href="/admin_quiz_result_management?orderBy=lastname&filterCategory=${filterCategorySubmit}&filterUsername=${filterUsernameSubmit}" style="color: white">User Full Name ${sortBy == "User" ? "↓" : ""}</a></th>
            <th><a href="/admin_quiz_result_management?orderBy=quiz_end_time&filterCategory=${filterCategorySubmit}&filterUsername=${filterUsernameSubmit}" style="color: white">Date ${sortBy == "Date" ? "↓" : ""}</a></th>
            <th><a href="/admin_quiz_result_management?orderBy=score&filterCategory=${filterCategorySubmit}&filterUsername=${filterUsernameSubmit}" style="color: white">Score ${sortBy == "Score" ? "↓" : ""}</a></th>
            <th>Result</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${adminHistoryQuizResults}" var="adminHistoryQuizResult" varStatus="loop">
                <tr>
                    <td>${adminHistoryQuizResult.historyquiz_id}</td>
                    <td>${adminHistoryQuizResult.quiz_name}</td>
                    <td>${adminHistoryQuizResult.category_name}</td>
                    <td>${adminHistoryQuizResult.username}</td>
                    <td>${adminHistoryQuizResult.lastname} ${adminHistoryQuizResult.firstname}</td>
                    <td>${adminHistoryQuizResult.quiz_end_time}</td>
                    <td>${adminHistoryQuizResult.score}</td>
                    <td><a href="/quiz-result/${adminHistoryQuizResult.historyquiz_id}" class="button">link</a></td>
                </tr>
            </c:forEach>
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
            form.action = "/filterQuiz?filter=" + selectedOption;
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
