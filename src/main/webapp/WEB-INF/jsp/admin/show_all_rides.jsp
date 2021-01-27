<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 4.01.21
  Time: 00:06
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
    <title><fmt:message key="showRides.title"/> </title>
</head>
<body>
<jsp:include page="header.jsp"/>
<table border="2px">
    <caption><fmt:message key="showRides.title"/> </caption>
    <tr>
        <th><fmt:message key="rideId"/> </th>
        <th><fmt:message key="clientLogin"/> </th>
        <th><fmt:message key="driverLogin"/> </th>
        <th><fmt:message key="startDate"/> </th>
        <th><fmt:message key="endDate"/> </th>
        <th><fmt:message key="clientMark"/> </th>
        <th><fmt:message key="driverMark"/> </th>
    </tr>
    <c:forEach var="ride" items="${rides}">
        <tr>
            <td>${ride.id}</td>
            <td>${ride.client.login}</td>
            <td>${ride.driver.login}</td>
            <td>${ride.startDate}</td>
            <td>${ride.endDate}</td>
            <td>${ride.clientMark}</td>
            <td>${ride.driverMark}</td>
        </tr>
    </c:forEach>
</table>
<p>${message}</p>
</body>
</html>
