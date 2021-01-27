<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 16.01.21
  Time: 22:43
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
    <title><fmt:message key="clientHome.title"/> </title>
</head>
<body>
<form method="post" action="client?command=client_logout">
    <button type="submit"><fmt:message key="logout"/> </button>
</form>
<h1><fmt:message key="clientHome.title"/> </h1>
<div>
    <a href="client?command=to_create_ride">
        <button><fmt:message key="createOrder"/> </button>
    </a>
</div>

<div>
    <a href="client?command=to_add_bank_card">
        <button><fmt:message key="addCard"/> </button>
    </a>
</div>
<div>
    <a href="client?command=to_update_client_phone_number">
        <button><fmt:message key="update"/> <fmt:message key="phoneNumber"/> </button>
    </a>
</div>
<div>
    <a href="client?command=to_edit_client_password">
        <button><fmt:message key="editPassword"/> </button>
    </a>
</div>
<div>
    <a href="client?command=to_client_show_ride_history">
        <button><fmt:message key="client.showAllRides"/> </button>
    </a>
</div>
</body>
</html>
