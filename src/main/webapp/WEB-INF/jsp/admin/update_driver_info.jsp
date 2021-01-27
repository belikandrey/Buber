<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 4.01.21
  Time: 00:05
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
    <title><fmt:message key="update"/> </title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div>
    <table border="2px">
        <caption><fmt:message key="updateDriverInfo.caption"/> </caption>
        <tr>
            <th><fmt:message key="name"/> </th>
            <th><fmt:message key="phoneNumber"/> </th>
            <th>Email</th>
            <th><fmt:message key="status"/> </th>
            <th><fmt:message key="rating"/> </th>
            <th><fmt:message key="new"/> <fmt:message key="status"/> </th>
            <th><fmt:message key="new"/> <fmt:message key="rating"/></th>
        </tr>
        <c:forEach var="driver" items="${drivers}">
            <form id="form" action="admin?command=update_driver_info" method="post">
                <tr>
                    <td>${driver.name}</td>
                    <td>${driver.phoneNumber}</td>
                    <td>${driver.email}</td>
                    <td>${driver.status}</td>
                    <td>${driver.rating}</td>
                    <input hidden name="login" value="${driver.login}">
                    <td><select id="status" name="newStatus">
                        <option value="" selected disabled hidden><fmt:message key="new"/> <fmt:message key="status"/></option>
                        <option value="ACTIVE"><fmt:message key="active"/> </option>
                        <option value="BANNED"><fmt:message key="banned"/></option>
                        <option value="WAITING_CONFIRM" ><fmt:message key="waitingConfirm"/> </option>
                    </select> </td>
                    <td><select id="rating" name="newRating">
                        <option value="" selected disabled hidden><fmt:message key="new"/> <fmt:message key="rating"/></option>
                        <option value="1"><fmt:message key="disgusting"/> </option>
                        <option value="2"><fmt:message key="bad"/> </option>
                        <option value="3" ><fmt:message key="tolerant"/> </option>
                        <option value="4" ><fmt:message key="normal"/> </option>
                        <option value="5" ><fmt:message key="fine"/> </option>
                    </select> </td>
                    <td><button type="submit" ><fmt:message key="submit"/> </button></td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <p>${message}</p>
</div>
</body>
</html>
