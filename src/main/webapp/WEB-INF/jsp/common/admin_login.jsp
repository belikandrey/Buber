<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 16.01.21
  Time: 22:08
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
    <title><fmt:message key="admin.login"/> </title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<p><fmt:message key="enterInfo"/> </p>
<div>
    <form method="post" action="home?command=admin_login">
        <p><fmt:message key="enterLogin"/> : <input name="login" type="text"></p>
        <p><fmt:message key="enterPassword"/> : <input name="password" type="password"></p>
        <p><c:out value="${message}"></c:out></p>
        <button type="submit"><fmt:message key="submit"/> </button>
    </form>
</div>
</body>
</html>
