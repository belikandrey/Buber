<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 28.12.20
  Time: 22:45
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
    <title><fmt:message key="homepage"/></title>
</head>

<body>
<div>
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="" selected disabled hidden>Lang</option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
            <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        </select>
    </form>
</div>
<div>
    <h1 class="header-home"><fmt:message key="youWishToContinue"/> : </h1>
</div>
<p>
    <a href="home?command=to_client_common">
        <button><fmt:message key="client"/> </button>
    </a>
</p>
<p>
    <a href="home?command=to_driver_common">
        <button><fmt:message key="driver"/> </button>
    </a>
</p>
<p>
    <a href="home?command=to_admin_login">
        <button style="font-size: xx-small"><fmt:message key="admin"/> </button>
    </a>
</p>
<br>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
