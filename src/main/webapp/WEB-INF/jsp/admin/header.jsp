<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 24.01.21
  Time: 01:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>
<form method="post" action="admin?command=logout">
<button type="submit"><fmt:message key="logout"/> </button>
</form>
<form method="post" action="admin?command=to_admin_home">
    <button type="submit"><fmt:message key="toHomepage"/></button>
</form>