<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 17:45
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
            <h1 class="big-font">Available rides</h1><br><br>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-left">
            <table id="example" class="table table-striped table-bordered" style="width:100%; color:black;">
                <thead>
                <tr class="table-warning">
                    <th>Start address</th>
                    <th>End address</th>
                    <th>Start date</th>
                    <th>Distance</th>
                    <th>Client name</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody style="color:black; font-weight: 700;">
                <c:forEach var="ride" items="${rides}">
                    <form action="driver?command=to_driver_submit_ride" method="post">
                        <tr>
                            <td>${ride.startLocation.address}</td>
                            <td>${ride.endLocation.address}</td>
                            <td>${ride.startDate}</td>
                            <td>${ride.distance}</td>
                            <td>${ride.client.name}</td>
                            <input name="ride_id" type="text" value="${ride.id}" hidden>
                            <td>
                                <div class="btn-group" style="width: 100%; ">
                                    <button type="submit" class="btn btn-secondary" aria-expanded="false">
                                        Submit
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="table-warning">
                    <th>Start address</th>
                    <th>End address</th>
                    <th>Start date</th>
                    <th>Distance</th>
                    <th>Client name</th>
                    <th>Action</th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</section>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>
