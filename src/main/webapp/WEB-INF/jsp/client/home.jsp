<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 6.02.21
  Time: 18:28
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
            <!-- Welcome to the Buber, <client_name> -->
            <h1 class="big-font"><fmt:message key="mainLabel"/> , ${client.name}!</h1><br><br>
        </div>
        <div class="row">
            <!--STATUS-->
            <h2 class="big-font middle-left"><fmt:message key="currentStatus"/> : ${client.status}</h2>
            <!--RATING-->
            <h2 class="big-font  middle-left"><fmt:message key="currentRating"/> : ${client.rating}</h2>
            <br>
            <!--BONUS%-->
            <h2 class="big-font middle-left"><fmt:message key="currentBonus"/> : ${client.bonusPercent}%</h2>
            <br>
            <div class="big-font  middle-left"><fmt:message key="currentPaymentType"/> : ${currentPaymentType}
                <div class="btn-group" style="width: 65%; padding-left: 15%;">
                    <fmt:message key="choosePaymentType"/>
                    <button style="margin-left: 15%;" type="button" class="btn btn-secondary dropdown-toggle"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="paymentType"/>
                    </button>
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="client?command=current_payment&type=cash"><fmt:message key="cash"/> </a>
                            </li>
                            <c:forEach items="${cards}" var="card">
                                <a class="dropdown-item" href="client?command=current_payment&type=${card.number}">${card.number}</a>
                            </c:forEach>
                        </ul>
                </div>
                <br>
                <div class="big-font middle-left">${message}</div>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="row justify-content-center" style="padding-left: 34%; margin-bottom: 5%;">
            <div class="col-8">
                <a href="client?command=to_create_ride">
                    <button style="width: 35%; height: 130%;" type="button" class="btn btn-secondary"><fmt:message key="createRide"/>
                    </button>
                </a>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-around" style="padding-left: 12%;">
            <div class="col-4">
                <a href="client?command=to_show_client_rides">
                    <button style="width: 75%; height: 120%;" type="button" class="btn btn-secondary"><fmt:message key="showMyRides"/>
                    </button>
                </a>
            </div>
            <div class="col-4">
                <div class="btn-group" style="width: 75%; ">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown"
                            aria-expanded="false">
                        <fmt:message key="editInfo"/>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="client?command=to_add_bank_card"><fmt:message key="addBankCard"/> </a></li>
                        <li><a class="dropdown-item" href="client?command=to_update_client_phone"><fmt:message key="updatePhoneNumber"/> </a></li>
                        <li><a class="dropdown-item" href="client?command=to_update_client_password"><fmt:message key="updatePassword"/> </a></li>
                    </ul>
                </div>
            </div>
            <div class="col-4">
                <a href="client?command=client_to_sign_as_driver">
                    <button style="width: 75%; height: 120%;" type="button" class="btn btn-secondary">
                        <fmt:message key="signAsDriver"/>
                    </button>
                </a>
            </div>
        </div>
    </div>
</section>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>