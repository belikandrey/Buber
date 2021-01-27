<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 21.01.21
  Time: 00:03
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
    <title><fmt:message key="rideHistory"/> </title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1><fmt:message key="rideHistory"/> : </h1>
<table border="2px">
    <tr>
        <th><fmt:message key="startLocation"/> </th>
        <th><fmt:message key="endLocation"/> </th>
        <th><fmt:message key="startDate"/> </th>
        <th><fmt:message key="endDate"/> </th>
        <th><fmt:message key="distance"/> </th>
        <th><fmt:message key="markForDriver"/> </th>
        <th><fmt:message key="new"/> <fmt:message key="markForDriver"/></th>
    </tr>
    <c:forEach var="ride" items="${rides}">
        <form method="post" action="client?command=update_driver_mark">
        <tr>
            <td>${ride.startLocation.address}</td>
            <td>${ride.endLocation.address}</td>
            <td>${ride.startDate}</td>
            <td>${ride.endDate}</td>
            <td>${ride.distance}</td>
            <td>${ride.driverMark}</td>
            <td><select id="newMark" name="newMark">
                <option value="1"><fmt:message key="disgusting"/> </option>
                <option value="2"><fmt:message key="bad"/> </option>
                <option value="3" ><fmt:message key="tolerant"/> </option>
                <option value="4" ><fmt:message key="normal"/> </option>
                <option value="5" ><fmt:message key="fine"/> </option>
            <td><button type="submit" ><fmt:message key="submit"/> </button></td>
            </select></td>
            <p><input name="ride_id" hidden value="${ride.id}"></p>
        </tr>
        </form>
    </c:forEach>
</table>
<p>${message}</p>
</body>
</html>
