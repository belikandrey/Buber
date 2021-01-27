<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 4.01.21
  Time: 00:05
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
    <title><fmt:message key="addAdmin.title"/></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1><fmt:message key="enterInfo"/> : </h1>
<div>
    <form method="post" action="admin?command=add_admin">
        <p><fmt:message key="enterLogin"/> : <input type="text" name="login"/></p><br>
        <p><fmt:message key="enterPassword"/> : <input type="password" name="password"/></p><br>
        <c:out value="${message}"></c:out>
        <button type="submit"><fmt:message key="submit"/></button>
    </form>
</div>
</body>
</html>
