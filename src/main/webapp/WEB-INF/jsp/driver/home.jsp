<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 16:47
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
            <!-- Welcome to the Buber, <driver_name> -->
            <h1 class="big-font" ><fmt:message key="mainLabel"/> , ${driver.name}!</h1><br><br>
        </div>
        <div class="row">
            <!--STATUS-->
            <h2 class="big-font middle-left" ><fmt:message key="currentStatus"/> : ${driver.status}</h2>
            <!--RATING-->
            <h2 class="big-font  middle-left"><fmt:message key="currentRating"/> : ${driver.rating}</h2>
            <br>
            <div class="big-font  middle-left"><fmt:message key="currentCar"/>/ : ${currentCar.brand} ${currentCar.model} ${currentCar.number}
                <div class="btn-group" style="width: 50%; padding-left: 15%;">
                    <fmt:message key="chooseCar"/>
                    <button style="margin-left: 15%;" type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="availbleCar"/>
                    </button>
                    <ul class="dropdown-menu">
                        <c:forEach items="${cars}" var="car">
                        <li><a class="dropdown-item" href="driver?command=current_car&number=${car.number}">${car.brand} ${car.model} ${car.number}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <br>
            </div>

        </div>
    </div>
    <div class="container">
        <div class="row justify-content-center"  style="padding-left: 30%; margin-bottom: 5%;">
            <div class="col-8">
                <a href="driver?command=to_available_rides"><button style="width: 45%; height: 130%;" type="button" class="btn btn-secondary">Available rides</button></a>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-around" style="padding-left: 12%;">
            <div class="col-6">
                <a href="driver?command=to_add_car"><button style="width: 75%; height: 120%;" type="button" class="btn btn-secondary"><fmt:message key="addCar"/> </button></a>
            </div>
            <div class="col-6">
                <a href="driver?command=to_update_driver_password"><button style="width: 75%; height: 120%;" type="button" class="btn btn-secondary"><fmt:message key="updatePassword"/> </button></a>
            </div>
        </div>
    </div>
</section>






<jsp:include page="../common/footer.jsp"/>
</body>
</html>
