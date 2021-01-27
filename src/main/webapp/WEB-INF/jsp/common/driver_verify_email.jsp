<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 17.01.21
  Time: 23:34
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
    <title><fmt:message key="verifyEmail"/> </title>
</head>
<body>
<p><fmt:message key="verifyEmail.text"/> : </p>
<form method="post" action="home?command=driver_verify_email">
    <p><fmt:message key="codeFromEmail"/> : <input name="userCode" type="text"></p>
    <c:out value="${message}"></c:out>
    <button type="submit"><fmt:message key="submit"/> </button>
</form>
</body>
</html>
