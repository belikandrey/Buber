<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 21.01.21
  Time: 00:04
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
    <title><fmt:message key="addCard"/></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<p><fmt:message key="enterInfo"/> : </p>
<form method="post" action="client?command=add_bank_card">
    <p><fmt:message key="cardNumber"/> : <input name="cardNumber"></p>
    <p><fmt:message key="cardDate"/> : <input name="cardDate"></p>
    <p><fmt:message key="cardCsc"/> : <input name="cardCsc"></p>
    <p>${message}</p>
    <p>
        <button type="submit"><fmt:message key="submit"/> </button>
    </p>
</form>
</body>
</html>
