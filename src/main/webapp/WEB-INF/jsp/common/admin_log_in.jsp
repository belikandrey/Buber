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
</head>
<body>
<jsp:include page="headerForJoin.jsp"/>

<section class="taxi-background">
    <div class="container">
        <div class="row inpForm" >
            <h2 class="big-font" >Welcome to the Buber, Admin!<br><br></h2>
            <form action="#">
                <div class="mb-3">
                    <label for="login" class="form-label">Login</label>
                    <input type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp">
                    <div style="color: red; font-weight: 500;" id="loginHelp" class="form-text">We'll never share your login with anyone else.</div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" id="exampleInputPassword1">
                </div>
                <div>
                    <button style="width: 150px; margin-top: 50px;" type="submit" class="btn btn-secondary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
