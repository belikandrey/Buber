<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 17.01.21
  Time: 00:51
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
    <title><fmt:message key="driver"/> </title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<p><fmt:message key="enterInfo"/> : </p>
<form method="post" action="home?command=driver_signin">
    <p><fmt:message key="name"/> : <input name="name" type="text"></p>
    <p><fmt:message key="phoneNumber"/> : <input name="phoneNumber" type="text"></p>
    <p>Email : <input name="email" type="email"></p>

    <p><fmt:message key="login"/> : <input name="login" type="text"></p>
    <p><fmt:message key="password"/> : <input name="password" type="password"></p>
    <p><fmt:message key="repeat"/> <fmt:message key="password"/> : <input name="repeatedPassword" type="password"></p>
    <p><c:out value="${message}"/></p>

    <p><fmt:message key="carInfo"/> : </p>
    <p><fmt:message key="carNumber"/> : <input name="carNumber" type="text"></p>
    <p><fmt:message key="carBrand"/> : <input name="carBrand" type="text"></p>
    <p><fmt:message key="carModel"/> : <input name="carModel" type="text"></p>
    <p><fmt:message key="carColor"/> : <input name="carColor" type="text"></p>

    <button type="submit"><fmt:message key="submit"/> </button>
</form>
</body>
</html>
