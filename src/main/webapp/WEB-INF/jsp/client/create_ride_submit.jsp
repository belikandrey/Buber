<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 19.01.21
  Time: 11:49
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
    <title><fmt:message key="createRide.title"/> </title>
</head>
<body>
<p><fmt:message key="startLocation"/> : ${ride.startLocation.address}</p>
<p><fmt:message key="endLocation"/> :${ride.endLocation.address}</p>
<p><fmt:message key="distance"/> : ${ride.distance}</p>
<p><fmt:message key="enterPaymentType"/> : </p>
<form method="post" action="client?command=client_submit_ride">
<select name="type_payment" id="type_payment">
    <option value="" selected disabled hidden><fmt:message key="paymentType"/> </option>
    <option vlue="cash"><fmt:message key="cash"/> </option>
    <c:forEach var="card" items="${cards}">
        <option value="${card.number}"><fmt:message key="card"/> : ${card.number}</option>
    </c:forEach>
</select>
    <input type="hidden" name="ride_id" value="${ride.id}">
<p><button type="submit">Submit</button></p>
    <p>${message}</p>
</form>
</body>
</html>
