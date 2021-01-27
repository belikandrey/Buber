<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 23.01.21
  Time: 11:07
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
    <title><fmt:message key="availableRides"/> </title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<table border="2px">
    <caption><fmt:message key="availableRides"/> :</caption>
    <tr>
        <th><fmt:message key="rideId"/> </th>
        <th><fmt:message key="clientLogin"/> </th>
        <th><fmt:message key="startLocation"/> </th>
        <th><fmt:message key="endLocation"/> </th>
        <th><fmt:message key="startDate"/> </th>
        <th><fmt:message key="startDate"/> </th>
    </tr>
    <c:forEach var="ride" items="${rides}">
        <form method="post" action="driver?command=to_driver_submit_ride">
            <tr>
                <td>${ride.id}</td>
                <td>${ride.client.login}</td>
                <td>${ride.startLocation.address}</td>
                <td>${ride.endLocation.address}</td>
                <td>${ride.startDate}</td>
                <td>${ride.distance}</td>
                <input hidden name="ride_id" value="${ride.id}">
                <td>
                    <button type="submit"><fmt:message key="submit"/> </button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<p>${message}</p>
</body>
</html>
