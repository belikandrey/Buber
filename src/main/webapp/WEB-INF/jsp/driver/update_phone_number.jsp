<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 23.01.21
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/><html>
<head>
    <title><fmt:message key="update"/> <fmt:message key="phoneNumber"/></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<p><fmt:message key="yourPhoneNumber"/> : ${driver.phoneNumber}</p>
<form method="post" action="driver?command=update_driver_phone_number">
    <p><fmt:message key="new"/> <fmt:message key="phoneNumber"/> : <input type="text" name="newPhoneNumber"></p>
    <p>${message}</p>
    <p>
        <button type="submit"><fmt:message key="submit"/> </button>
    </p>
</form>
</body>
</html>
