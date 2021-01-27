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
    <title><fmt:message key="payments"/> </title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<table border="2px">
    <caption><fmt:message key="payments"/></caption>
    <tr>
        <th><fmt:message key="rideId"/> </th>
        <th><fmt:message key="paymentType"/> </th>
        <th><fmt:message key="price"/> </th>
        <th><fmt:message key="transactionNumber"/> </th>
    </tr>
    <c:forEach var="payment" items="${payments}">
        <tr>
            <td>${payment.ride.id}</td>
            <td>${payment.paymentType}</td>
            <td>${payment.price}</td>
            <td>${payment.transactionNumber}</td>
        </tr>
    </c:forEach>
</table>
<p>${message}</p>
</body>
</html>
