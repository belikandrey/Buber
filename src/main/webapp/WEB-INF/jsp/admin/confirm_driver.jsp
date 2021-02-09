<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 8.02.21
  Time: 17:30
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


<section class="taxi-background" style="padding-bottom: 5%;">
    <div class="container">
        <div class="row">
            <h1 class="big-font"><fmt:message key="notConfirmedDrivers"/> </h1><br><br>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-left">
            <table id="example" class="table table-striped table-bordered" style="width:100%; color:black;">
                <thead>
                <tr class="table-warning">
                    <th><fmt:message key="name"/> </th>
                    <th><fmt:message key="phoneNumber"/> </th>
                    <th><fmt:message key="email"/> </th>
                    <th><fmt:message key="submit"/> </th>
                </tr>
                </thead>
                <tbody style="color:black; font-weight: 700;">
                <c:forEach var="driver" items="${drivers}">
                    <form action="admin?command=confirm_driver" method="post">
                        <tr>
                            <td>${driver.name}</td>
                            <td>${driver.phoneNumber}</td>
                            <td>${driver.email}</td>
                            <input name="login" type="text" value="${driver.login}" hidden>
                            <td>
                                <div class="btn-group" style="width: 100%; ">
                                    <button type="submit" class="btn btn-secondary" aria-expanded="false">
                                        <fmt:message key="submit"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="table-warning">
                    <th><fmt:message key="name"/> </th>
                    <th><fmt:message key="phoneNumber"/> </th>
                    <th><fmt:message key="email"/> </th>
                    <th><fmt:message key="submit"/> </th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</section>



<jsp:include page="../common/footer.jsp"/>

</body>
</html>
