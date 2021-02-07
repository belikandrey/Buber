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
</head>
<body>
<jsp:include page="header.jsp"/>


<section class="taxi-background" style="padding-bottom: 5%;">
    <div class="container">
        <div class="row">
            <!-- Welcome to the Buber, <client_name> -->
            <h1 class="big-font">Welcome to the Buber, ${client.name}!</h1><br><br>
        </div>
        <div class="row">
            <!--STATUS-->
            <h2 class="big-font middle-left">Current status : ${client.status}</h2>
            <!--RATING-->
            <h2 class="big-font  middle-left">Current rating : ${client.rating}</h2>
            <br>
            <!--BONUS%-->
            <h2 class="big-font middle-left">Current bonus : ${client.bonusPercent}%</h2>
            <br>
            <div class="big-font  middle-left">Current payment : ${currentPaymentType}
                <div class="btn-group" style="width: 65%; padding-left: 15%;">
                    Choose payment
                    <button style="margin-left: 15%;" type="button" class="btn btn-secondary dropdown-toggle"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        Payment type
                    </button>
                    <!--CARRRRRRRRdS-->
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="client?command=current_payment&type=cash">Cash</a>
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
            <!-- Welcome to the Buber, <client_name> -->
            <div class="col-8">
                <a href="client?command=to_create_ride">
                    <button style="width: 35%; height: 130%;" type="button" class="btn btn-secondary">Create ride
                    </button>
                </a>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-around" style="padding-left: 12%;">
            <div class="col-4">
                <a href="client?command=to_show_client_rides">
                    <button style="width: 75%; height: 120%;" type="button" class="btn btn-secondary">Show my rides
                    </button>
                </a>
            </div>
            <div class="col-4">
                <div class="btn-group" style="width: 75%; ">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown"
                            aria-expanded="false">
                        Edit info
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="client?command=to_add_bank_card">Add bank card</a></li>
                        <li><a class="dropdown-item" href="client?command=to_update_client_phone">Update phone number</a></li>
                        <li><a class="dropdown-item" href="client?command=to_update_client_password">Update password</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-4">
                <a href="client?command=client_to_sign_as_driver">
                    <button style="width: 75%; height: 120%;" type="button" class="btn btn-secondary">Sign in as
                        driver
                    </button>
                </a>
            </div>
        </div>
    </div>
</section>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>