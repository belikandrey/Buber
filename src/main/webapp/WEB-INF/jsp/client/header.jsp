<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 24.01.21
  Time: 01:02
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
<form method="post" action="client?command=logout">
    <button type="submit"><fmt:message key="logout"/> </button>
</form>
<form method="post" action="client?command=to_client_home">
    <button><fmt:message key="toHomepage"/> </button>
</form>