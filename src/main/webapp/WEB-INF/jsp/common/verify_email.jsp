<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 6.02.21
  Time: 19:22
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
            <h2 style="font-size: 32px;" class="big-font" >Enter code from your email</h2>
            <form action="home?command=verify_email" method="post">
                <div class="mb-3">
                    <label for="userCode" class="form-label">Code</label>
                    <input type="text" class="form-control" id="userCode" name="userCode" aria-describedby="codeHelp">
                    <div style="color: red; font-weight: 500;" id="codeHelp" class="form-text">We send code for verification on your email. Put it here</div>
                </div>
                <button type="submit" class="btn btn-secondary">Submit</button>
            </form>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>


</body>
</html>
