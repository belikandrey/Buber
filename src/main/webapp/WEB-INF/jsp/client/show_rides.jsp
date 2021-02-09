<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://Buber.com/functions" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title>Buber</title>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.23/js/dataTables.bootstrap4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.23/css/dataTables.bootstrap4.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="shortcut icon" href="/resources/img/Buber_ico.ico"/>

</head>
<body>


<jsp:include page="header.jsp"/>


<section class="taxi-background" style="padding-bottom: 5%;">
    <div class="container">
        <div class="row">
            <h1 class="big-font"><fmt:message key="yourRides"/> </h1><br><br>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-left">
            <table id="example" class="table table-striped table-bordered" style="width:100%; color:black;">
                <thead>
                <tr class="table-warning">
                    <th><fmt:message key="startAddress"/> </th>
                    <th><fmt:message key="endAddress"/> </th>
                    <th><fmt:message key="startDate"/> </th>
                    <th><fmt:message key="distance"/></th>
                    <th><fmt:message key="markForDriver"/> </th>
                    <th><fmt:message key="newMarkForDriver"/> </th>
                </tr>
                </thead>
                <tbody style="color:black; font-weight: 700;">
                <c:forEach var="ride" items="${rides}">
                    <tr>
                        <td>${ride.startLocation.address}</td>
                        <td>${ride.endLocation.address}</td>
                        <td>${f:formatLocalDateTime(ride.startDate,'dd.MM.yyyy HH:mm')}</td>
                        <td>${ride.distance}</td>
                        <td>${ride.driverMark}</td>
                        <td>
                            <div class="btn-group" style="width: 100%; ">
                                <button type="button" class="btn btn-light dropdown-toggle" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                    New mark
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="client?command=update_driver_mark&mark=1&ride_id=${ride.id}"><fmt:message key="disquasting"/> </a></li>
                                    <li><a class="dropdown-item" href="client?command=update_driver_mark&mark=2&ride_id=${ride.id}"><fmt:message key="bad"/> </a></li>
                                    <li><a class="dropdown-item" href="client?command=update_driver_mark&mark=3&ride_id=${ride.id}"><fmt:message key="tolerant"/></a></li>
                                    <li><a class="dropdown-item" href="client?command=update_driver_mark&mark=4&ride_id=${ride.id}"><fmt:message key="normal"/></a></li>
                                    <li><a class="dropdown-item" href="client?command=update_driver_mark&mark=5&ride_id=${ride.id}"><fmt:message key="fine"/></a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="table-warning">
                    <th><fmt:message key="startAddress"/> </th>
                    <th><fmt:message key="endAddress"/> </th>
                    <th><fmt:message key="startDate"/> </th>
                    <th><fmt:message key="distance"/></th>
                    <th><fmt:message key="markForDriver"/> </th>
                    <th><fmt:message key="newMarkForDriver"/> </th>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</section>




<script type="text/javascript">
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>




<jsp:include page="../common/footer.jsp"/>

</body>
</html>
