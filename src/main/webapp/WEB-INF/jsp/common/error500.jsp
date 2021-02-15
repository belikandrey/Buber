<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 15.02.21
  Time: 02:51
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
    <title>Error</title>
    <link rel="shortcut icon" href="/resources/img/Buber_ico.ico"/>
</head>
<body>
<jsp:include page="header.jsp"/>


<section style="background: url(/resources/img/error500img.jpg);">
    <div class="container">
        <div class="row">
            <div>
                <h2 class="big-font" style="margin-top:15%;font-size: 75px; font-weight: bold;">ERROR 500</h2>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col" style="padding-left: 34%; margin-top: 5%;">
                <h2 style="font-size: 40px; color: red;">
                    <fmt:message key="error500Label"/>
                </h2>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col" style="padding-left: 27%; margin-bottom: 5%;">
                <div style="font-size: 25px; color:red;">
                    <fmt:message key="error500Text"/>
                </div>
            </div>
        </div>
    </div>
</section>


<jsp:include page="footer.jsp"/>
</body>
</html>
