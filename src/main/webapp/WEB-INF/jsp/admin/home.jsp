<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 17.01.21
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
<fmt:setBundle basename="message"/><html>
<head>
    <title><fmt:message key="adminHome.title"/> </title>
</head>
<body>
<form method="post" action="admin?command=logout">
    <button type="submit"><fmt:message key="logout"/> </button>
</form>
<a href="admin?command=to_update_driver_info">
    <button><fmt:message key="admin.updateDriverInfo"/> </button>
</a>
<a href="admin?command=to_update_client_info">
    <button><fmt:message key="admin.updateClientInfo"/> </button>
</a>
<a href="admin?command=to_add_admin">
    <button><fmt:message key="addAdmin.title"/></button>
</a>
<a href="admin?command=to_show_all_rides">
    <button><fmt:message key="showAllRides"/> </button>
</a>
<a href="admin?command=to_show_all_payments">
    <button><fmt:message key="showAllPayments"/> </button>
</a>
<a href="admin?command=to_confirm_driver">
    <button><fmt:message key="confirmDriver.title"/> </button>
</a>
</body>
</html>
