<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 23.01.21
  Time: 19:39
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
    <title><fmt:message key="editPassword"/> </title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<form method="post" action="driver?command=edit_driver_password">
    <p><fmt:message key="enterNewPassword"/> : <input type="password" name="newPassword"></p>
    <p><button type="submit"><fmt:message key="submit"/> </button></p>
</form>
</body>
</html>
