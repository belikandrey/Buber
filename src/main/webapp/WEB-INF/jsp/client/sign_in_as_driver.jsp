<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/><html>
<head>
    <title>Buber</title>
</head>
<body>
<jsp:include page="header.jsp"/>




<section class="taxi-background">
    <div class="container">
        <form action="client?command=client_sign_as_driver" method="post">
            <div class="row">
                <div style="padding-left: 25%;" class="col col-lg-9">
                    <h2 style="padding-left: 30%;">Car info</h2>
                    <div class="mb-3">
                        <label for="car_number" class="form-label">Car number</label>
                        <input type="text" class="form-control" id="car_number" name="car_number" aria-describedby="car_numberHelp">
                        <div style="color: red; font-weight: 500;" id="car_numberHelp" class="form-text">Enter car number(6666 AC-7).</div>
                    </div>
                    <div class="mb-3">
                        <label for="car_brand" class="form-label">Car brand</label>
                        <input type="text" class="form-control" id="car_brand" name="car_brand" aria-describedby="car_brandHelp">
                        <div style="color: red; font-weight: 500;" id="car_brandHelp" class="form-text">Enter car brand(BMW, Audi, Ford, e.c.).</div>
                    </div>
                    <div class="mb-3">
                        <label for="car_model" class="form-label">Car model</label>
                        <input type="text" class="form-control" id="car_model" name="car_model" aria-describedby="car_modelHelp">
                        <div style="color: red; font-weight: 500;" id="car_modelHelp" class="form-text">Enter car model(M5, R8, Mustang, e.c.).</div>
                    </div>
                    <div class="mb-3">
                        <label for="car_color" class="form-label">Car color</label>
                        <input type="text" class="form-control" id="car_color" name="car_color" aria-describedby="car_colorHelp">
                        <div style="color: red; font-weight: 500;" id="car_colorHelp" class="form-text">Enter color of car(White, Blue, Black).</div>
                    </div>
                </div>
                <br>
                <div style="margin-top: 4%;" class="row justify-content-center">
                    <div style="" class="col-2">
                        <button style=" width: 250px; height: 75px;" type="submit" class="btn btn-secondary">
                            Submit
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
