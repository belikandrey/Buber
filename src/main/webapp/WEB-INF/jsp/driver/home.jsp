<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 16.01.21
  Time: 23:14
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
    <title><fmt:message key="driverHome.title"/> </title>
</head>
<body>
<form method="post" action="driver?command=logout">
    <button type="submit"><fmt:message key="logout"/> </button>
</form>
<div>
    <a href="driver?command=to_available_rides">
        <button><fmt:message key="availableRides"/> </button>
    </a>
</div>
<div>
    <a href="driver?command=to_update_driver_phone_number">
        <button><fmt:message key="update"/> <fmt:message key="phoneNumber"/> </button>
    </a>
</div>

<div>
    <a href="driver?command=to_add_car">
        <button><fmt:message key="addCar"/> </button>
    </a>
</div>

<div>
    <a href="driver?command=to_edit_driver_password">
        <button><fmt:message key="editPassword"/> </button>
    </a>
</div>
<p>${message}</p>

</body>
</html>
