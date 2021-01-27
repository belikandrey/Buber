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
    <title><fmt:message key="confirmDriver.title"/></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<table border="2px">
    <caption><fmt:message key="confirmDriver.tableCaption"/> :</caption>
    <tr>
        <th><fmt:message key="name"/></th>
        <th><fmt:message key="phoneNumber"/> </th>
        <th>Email</th>
    </tr>
    <c:forEach var="driver" items="${drivers}">
        <form action="admin?command=confirm_driver" method="post">
            <tr>
                <input type="hidden" name="login" value="${driver.login}">
                <td>${driver.name}</td>
                <td>${driver.phoneNumber}</td>
                <td>${driver.email}</td>
                <td>
                    <button type="submit"><fmt:message key="submit"/></button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<p>${message}</p>
</body>
</html>
