<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 17:29
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
        <form action="driver?command=add_car" method="post">
            <div class="row">

                <h2 style="padding-left: 45%;padding-top: 5%;"><fmt:message key="carInfo"/> </h2>
                <div class="mb-3">
                    <label for="car_number" class="form-label"><fmt:message key="carNumber"/> </label>
                    <input type="text" class="form-control" id="car_number" name="car_number" aria-describedby="car_numberHelp">
                    <div style="color: red; font-weight: 500;" id="car_numberHelp" class="form-text"><fmt:message key="carNumberHelp"/> (6666 AC-7).</div>
                </div>
                <div class="mb-3">
                    <label for="car_brand" class="form-label"><fmt:message key="carBrand"/> </label>
                    <input type="text" class="form-control" id="car_brand" name="car_brand" aria-describedby="car_brandHelp">
                    <div style="color: red; font-weight: 500;" id="car_brandHelp" class="form-text"><fmt:message key="carBrandHelp"/>(BMW, Audi, Ford, e.c.).</div>
                </div>
                <div class="mb-3">
                    <label for="car_model" class="form-label"><fmt:message key="carModel"/></label>
                    <input type="text" class="form-control" id="car_model" name="car_model" aria-describedby="car_modelHelp">
                    <div style="color: red; font-weight: 500;" id="car_modelHelp" class="form-text"><fmt:message key="carModelHelp"/>(M5, R8, Mustang, e.c.).</div>
                </div>
                <div class="mb-3">
                    <label for="car_color" class="form-label"><fmt:message key="carColor"/> r</label>
                    <input type="text" class="form-control" id="car_color" name="car_color" aria-describedby="car_colorHelp">
                    <div style="color: red; font-weight: 500;" id="car_colorHelp" class="form-text"><fmt:message key="carColorHelp"/> (White, Blue, Black).</div>
                </div>
            </div>
            <br>
            <div style="margin-top: 4%;" class="row justify-content-center">
                <div style="" class="col-2">
                    <button style=" width: 250px; height: 75px;" type="submit" class="btn btn-secondary">
                        <fmt:message key="submit"/>
                    </button>
                </div>
            </div>
    </div>
    </form>
    </div>

</section>






<jsp:include page="../common/footer.jsp"/>

</body>
</html>
