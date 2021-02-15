<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 6.02.21
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>Buber</title>
    <link rel="shortcut icon" href="/resources/img/Buber_ico.ico"/>
</head>
<body>
<jsp:include page="headerForJoin.jsp"/>

<section class="taxi-background">
    <div class="container">
        <div class="row inpForm" >
            <h2 class="big-font" ><fmt:message key="mainLabel"/> , Admin!<br><br></h2>
            <form action="home?command=admin_login" method="post">
                <div class="mb-3">
                    <label for="login" class="form-label"><fmt:message key="login"/> </label>
                    <input type="text" class="form-control" pattern="^[(\w)-]{5,20}" required="" id="login" name="login" aria-describedby="loginHelp">
                    <div style="color: red; font-weight: 500;" id="loginHelp" class="form-text"><fmt:message key="loginHelp"/> </div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label"><fmt:message key="password"/> </label>
                    <input type="password" pattern="^[A-Za-z]\w{4,29}$" required="" class="form-control" name="password" id="exampleInputPassword1">
                </div>
                <div class="row" >
                    <p style="color: red; font-weight: bold; font-size: x-large;">${message}</p>
                </div>
                <div>
                    <button style="width: 150px; margin-top: 50px;" type="submit" class="btn btn-secondary"><fmt:message key="submit"/> </button>
                </div>
            </form>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
