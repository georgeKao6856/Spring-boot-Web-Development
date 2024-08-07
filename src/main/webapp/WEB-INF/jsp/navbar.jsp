<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-expand-lg navbar-dark navbar-purple mb-4">
    <img src="https://media.licdn.com/dms/image/sync/D4D27AQEihPVi0yiLdg/articleshare-shrink_800/0/1711444875006?e=2147483647&v=beta&t=M5w7EVEtv-gwGkyydfth042Id9QZ9dEz_4m4SOv4H5s" alt="logo" class="mr-2" height="30">
    <a class="navbar-brand" href="#">Capture the Flag</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse mr-auto" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
            <c:if test="${not empty sessionScope.user}">
                <li class="nav-item ${pageContext.request.requestURI == '/WEB-INF/jsp/home.jsp' ? 'active' : ''}">
                    <a class="nav-link" href="/home">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </c:if>
            <li class="nav-item ${pageContext.request.requestURI == '/WEB-INF/jsp/contactus.jsp' ? 'active' : ''}">
                <a class="nav-link" href="/contactus">Contact</a>
            </li>
            <c:if test="${isAdmin == true}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Admin Console
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/admin_home">Admin Home</a>
                        <a class="dropdown-item" href="/admin_user_management">User Management</a>
                        <a class="dropdown-item" href="/admin_quiz_result_management?orderBy=quiz_end_time&filterUsername=All&filterCategory=All">Quiz Result Management</a>
                        <a class="dropdown-item" href="/admin_question_management">Question Management</a>
                        <a class="dropdown-item" href="/admin_contact_us">Contact Us Management</a>
                    </div>
                </li>
            </c:if>
        </ul>

        <ul class="navbar-nav">
            <li class="nav-item">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <a href="/logout" class="button">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/login" class="button">Login</a>
                    </c:otherwise>
                </c:choose>

            </li>
        </ul>
    </div>
</nav>