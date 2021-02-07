<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 18:12
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
            <h1 class="big-font" >End ride!</h1><br><br>
        </div>
        <div class="row">
            <!--STATUS-->
            <h2 class="big-font middle-left" >Start address : ${ride.startLocation.address}</h2>
            <!--RATING-->
            <h2 class="big-font  middle-left">End address : ${ride.endLocation.address}</h2>
            <br>
            <!--BONUS%-->
            <h2 class="big-font middle-left">Price : ${payment.price}</h2>
            <br>

            <h2 class="big-font middle-left">Payment type : ${payment.paymentType}</h2>
            <br>


            <form method="post" action="driver?command=end_ride">
            <div class="col-4">
                <select  id="clientMark" name="clientMark" style="width: 75%; height: 55%;" class="btn-secondary">
                    <option value="1">Disgusting </option>
                    <option value="2">Bad </option>
                    <option value="3">Tolerant</option>
                    <option value="4">Normal </option>
                    <option value="5">Fine </option>
                </select>
                <p><input hidden name="ride_id" value="${ride.id}"></p>
            </div>


            <div class="container" style="margin-top: 5%;">
                <div class="row justify-content-center"  style="padding-left: 34%; ">
                    <!-- Welcome to the Buber, <client_name> -->
                    <div class="col-8">
                            <button style="width: 40%; height: 130%;" type="submit" class="btn btn-secondary">Submit!</button>
                    </div>
                </div>
            </div>
            </form>

        </div>
    </div>

</section>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>
