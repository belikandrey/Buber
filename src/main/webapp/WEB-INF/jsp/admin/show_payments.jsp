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
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.23/js/dataTables.bootstrap4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.23/css/dataTables.bootstrap4.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
<jsp:include page="header.jsp"/>



<section class="taxi-background" style="padding-bottom: 5%;">
    <div class="container">
        <div class="row">
            <h1 class="big-font"><fmt:message key="allPayments"/> </h1><br><br>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-left">
            <table id="example" class="table table-striped table-bordered" style="width:100%; color:black;">
                <thead>
                <tr class="table-warning">
                    <th><fmt:message key="transactionNumber"/> </th>
                    <th><fmt:message key="paymentType"/> </th>
                    <th><fmt:message key="price"/> </th>
                    <th><fmt:message key="clientLogin"/> </th>
                    <th><fmt:message key="driverLogin"/> </th>
                </tr>
                </thead>
                <tbody style="color:black; font-weight: 700;">
                <c:forEach var="payment" items="${payments}">
                    <tr>
                        <td>${payment.transactionNumber}</td>
                        <td>${payment.paymentType}</td>
                        <td>${payment.price}</td>
                        <td>${payment.ride.client.login}</td>
                        <td>${payment.ride.driver.login}</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="table-warning">
                    <th><fmt:message key="transactionNumber"/> </th>
                    <th><fmt:message key="paymentType"/> </th>
                    <th><fmt:message key="price"/> </th>
                    <th><fmt:message key="clientLogin"/> </th>
                    <th><fmt:message key="driverLogin"/> </th>
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
