<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 8.02.21
  Time: 16:56
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
    <title>Buber</title>
    <link rel="shortcut icon" href="/resources/img/Buber_ico.ico"/>

</head>
<body>
<jsp:include page="header.jsp"/>




<section class="taxi-background" >
    <div class="container">
        <div class="row">
            <!-- Welcome to the Admin, <admin_login> -->
            <h1 class="big-font" ><fmt:message key="mainLabel"/> , ${admin.login}!</h1><br><br>
        </div>
    </div>
    <div class="container" style="margin-top: 15%;">
        <div class="row justify-content-end">
            <div class="col-3">
                <a href="admin?command=to_add_admin"><button style="width: 200px; height: 55px;" type="button" class="btn btn-secondary"><fmt:message key="addAdmin"/> </button></a>
            </div>
            <div class="col-3">
                <a href="admin?command=to_confirm_driver"><button style="width: 200px; height: 55px;" type="button" class="btn btn-secondary"><fmt:message key="confirmDriver"/> </button></a>
            </div>
            <div class="col-3">
                <div class="btn-group">
                    <button style="width: 200px; height: 55px;" type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="show"/>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="admin?command=to_show_rides"><fmt:message key="rides"/> </a></li>
                        <li><a class="dropdown-item" href="admin?command=to_show_payments"><fmt:message key="payments"/> </a></li>
                    </ul>
                </div>
            </div>
            <div class="col-3">
                <div class="btn-group">
                    <button style="width: 230px; height: 55px;" type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="updateInfo"/>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="admin?command=to_update_drivers"><fmt:message key="drivers"/></a></li>
                        <li><a class="dropdown-item" href="admin?command=to_update_clients"><fmt:message key="clients"/> </a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>



<jsp:include page="../common/footer.jsp"/>
</body>
</html>
