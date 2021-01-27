<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 23.01.21
  Time: 12:37
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
    <title><fmt:message key="endRide"/> </title>
</head>
<body>
<p><fmt:message key="startLocation"/> : ${ride.startLocation.address}</p>
<p><fmt:message key="endLocation"/> : ${ride.endLocation.address}</p>
<p><fmt:message key="distance"/> : ${ride.distance}</p>
<p><fmt:message key="paymentType"/> : ${payment.paymentType}</p>
<form method="post" action="driver?command=driver_end_ride">
<select id="clientMark" name="clientMark">
    <option value="1"><fmt:message key="disgusting"/> </option>
    <option value="2"><fmt:message key="bad"/> </option>
    <option value="3"><fmt:message key="tolerant"/> </option>
    <option value="4"><fmt:message key="normal"/> </option>
    <option value="5"><fmt:message key="fine"/> </option>
    <p><input hidden name="ride_id" value="${ride.id}"></p>
</select>
    <p><button type="submit"><fmt:message key="submit"/> </button></p>
</form>
</body>
</html>
