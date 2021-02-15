<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 6.02.21
  Time: 18:30
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
<jsp:include page="headerForJoin.jsp"/>


<section class="taxi-background">
    <div class="container">
        <div class="row inpForm">
            <h2 class="big-font"><fmt:message key="mainLabel"/> <br></h2>
        </div>
    </div>
    <div class="container">
        <form action="home?command=driver_sign_in" method="post">
            <div class="row">

                <div class="col col-lg-5">
                    <h2 style="padding-left: 20%;"><fmt:message key="personalInfo"/></h2>
                    <div class="mb-3">
                        <label for="name" class="form-label"><fmt:message key="name"/> </label>
                        <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp">
                        <div style="color: red; font-weight: 500;" id="nameHelp" class="form-text"><fmt:message
                                key="nameHelp"/></div>
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label"><fmt:message key="phoneNumber"/> </label>
                        <input type="tel" class="form-control" id="phone" name="phone" aria-describedby="phoneHelp">
                        <div style="color: red; font-weight: 500;" id="phoneHelp" class="form-text"><fmt:message
                                key="phoneNumberHelp"/></div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label"><fmt:message key="email"/> </label>
                        <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp">
                        <div style="color: red; font-weight: 500;" id="emailHelp" class="form-text"><fmt:message
                                key="emailHelp"/></div>
                    </div>
                    <div class="mb-3">
                        <label for="login" class="form-label"><fmt:message key="login"/> </label>
                        <input type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp">
                        <div style="color: red; font-weight: 500;" id="loginHelp" class="form-text"><fmt:message
                                key="loginHelp"/></div>
                    </div>
                    <div class="mb-3">
                        <label for="password1" class="form-label"><fmt:message key="password"/> </label>
                        <input type="password" class="form-control" name="password" id="password1"
                               aria-disabledby="passwordHelp1">
                        <div style="color: red; font-weight: 500;" id="passwordHelp1" class="form-text"><fmt:message
                                key="passwordHelp"/></div>
                    </div>
                    <div class="mb-3">
                        <label for="password2" class="form-label"><fmt:message key="repeatPassword"/> </label>
                        <input type="password" class="form-control" name="repeated_password" id="password2"
                               aria-disabledby="passwordHelp2">
                    </div>

                </div>
                <div class="col col-lg-2">

                </div>
                <div class="col col-lg-5">
                    <h2 style="padding-left: 25%;"><fmt:message key="carInfo"/></h2>
                    <div class="mb-3">
                        <label for="car_number" class="form-label"><fmt:message key="carNumber"/> </label>
                        <input type="text" class="form-control" id="car_number" name="car_number"
                               aria-describedby="car_numberHelp">
                        <div style="color: red; font-weight: 500;" id="car_numberHelp" class="form-text"><fmt:message
                                key="carNumberHelp"/> (6666 AC-7).
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="car_brand" class="form-label"><fmt:message key="carBrand"/> </label>
                        <input type="text" class="form-control" id="car_brand" name="car_brand"
                               aria-describedby="car_brandHelp">
                        <div style="color: red; font-weight: 500;" id="car_brandHelp" class="form-text"><fmt:message
                                key="carBrandHelp"/> (BMW, Audi, Ford, e.c.).
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="car_model" class="form-label"><fmt:message key="carModel"/> </label>
                        <input type="text" class="form-control" id="car_model" name="car_model"
                               aria-describedby="car_modelHelp">
                        <div style="color: red; font-weight: 500;" id="car_modelHelp" class="form-text"><fmt:message
                                key="carModelHelp"/> (M5, R8, Mustang, e.c.).
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="car_color" class="form-label"><fmt:message key="carColor"/> </label>
                        <input type="text" class="form-control" id="car_color" name="car_color"
                               aria-describedby="car_colorHelp">
                        <div style="color: red; font-weight: 500;" id="car_colorHelp" class="form-text"><fmt:message
                                key="carColorHelp"/> (White, Blue, Black).
                        </div>
                    </div>
                </div>
                <br>

                <div style="margin-top: 4%;" class="row justify-content-center">
                    <div class="col-8">
                        <p style="color:red; font-weight: bold; font-size: x-large;">${message}</p>
                    </div>
                </div>
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


<jsp:include page="footer.jsp"/>

</body>
</html>
