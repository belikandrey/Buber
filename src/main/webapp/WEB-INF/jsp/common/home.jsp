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
    <title>Buber</title>
    <link rel="shortcut icon" href="/resources/img/Buber_ico.ico"/>

</head>
<body>
<jsp:include page="header.jsp"/>

<section class="taxi-background">
    <div class="container">
        <div class="row">
            <h1 class="big-font" ><fmt:message key="mainLabel"/> !<br><br><br><p><a class="biggest-text" style="color: black;" href="tel:#">666-66-66</a></p></h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <h2 style="text-align: center; padding-top: 5%;"><fmt:message key="carTypes"/> </h2>
        <div class="divider divider-default"></div>
        <div class="row colomns-padd">
            <div class="col">

                <div class="box-image">
                    <img src="/resources/img/car_type1.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i><fmt:message key="basic"/></i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li><fmt:message key="lotsOfLocations"/></li>
                            <li><fmt:message key="waitingOver"/></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="box-image">
                    <img src="/resources/img/car_type2.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i><fmt:message key="busines"/></i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li><fmt:message key="lotsOfLocations"/></li>
                            <li><fmt:message key="waitingOver"/></li>
                            <li><fmt:message key="luggageTransportation"/></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="box-image">
                    <img src="/resources/img/car_type3.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i><fmt:message key="minivan"/></i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li><fmt:message key="lotsOfLocations"/></li>
                            <li><fmt:message key="waitingOver"/></li>
                            <li><fmt:message key="luggageTransportation"/></li>
                            <li><fmt:message key="babySeat"/></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="box-image">
                    <img src="/resources/img/car_type4.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i><fmt:message key="truck"/></i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li><fmt:message key="lotsOfLocations"/></li>
                            <li><fmt:message key="waitingOver"/></li>
                            <li><fmt:message key="luggageTransportation"/></li>
                            <li><fmt:message key="babySeat"/></li>
                            <li><fmt:message key="animalTransportation"/></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="bg-grey">
    <div class="container quest">
        <div class="row">
            <div class="col">
                <div class="img-wrapper">
                    <img src="/resources/img/questions.jpg">
                </div>
            </div>
            <div class="col">
                <div class="questions">
                    <h2><fmt:message key="commonQuestions"/></h2>
                    <br>
                    <p class="quetions-info">
                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="false" aria-controls="collapse1">
                            <fmt:message key="howOrderTaxi"/>
                        </button>
                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2" aria-expanded="false" aria-controls="collapse2e">
                            <fmt:message key="howPayForOrderTaxi"/>
                        </button>
                    </p>
                    <div class="collapse" id="collapse1">
                        <div class="card card-body">
                            <fmt:message key="howOrderTaxiAnswer"/>
                        </div>
                    </div>
                    <div class="collapse" id="collapse2">
                        <div class="card card-body">
                            <fmt:message key="howPayForOrderTaxiAnswer"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
