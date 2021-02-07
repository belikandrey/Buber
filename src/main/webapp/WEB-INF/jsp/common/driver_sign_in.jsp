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
</head>
<body>
<jsp:include page="headerForJoin.jsp"/>




<section class="taxi-background">
    <div class="container">
        <div class="row inpForm" >
            <h2 class="big-font" >Welcome to the Buber!<br></h2>
        </div>
    </div>
    <div class="container">
        <form action="#">
            <div class="row">

                <div class="col col-lg-5">
                    <h2 style="padding-left: 20%;">Personal info</h2>
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp">
                        <div style="color: red; font-weight: 500;" id="nameHelp" class="form-text">Enter your real name.</div>
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone number</label>
                        <input type="tel" class="form-control" id="phone" name="phone" aria-describedby="phoneHelp">
                        <div style="color: red; font-weight: 500;" id="phoneHelp" class="form-text">Enter phone number without spaces and hyphen.</div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp">
                        <div style="color: red; font-weight: 500;" id="emailHelp" class="form-text">Enter right email. If you forget your password: we will send it to you by email. </div>
                    </div>
                    <div class="mb-3">
                        <label for="login" class="form-label">Login</label>
                        <input type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp">
                        <div style="color: red; font-weight: 500;" id="loginHelp" class="form-text">We'll never share your login with anyone else.</div>
                    </div>
                    <div class="mb-3">
                        <label for="password1" class="form-label">Password</label>
                        <input type="password" class="form-control" name="password" id="password1" aria-disabledby="passwordHelp1">
                        <div style="color: red; font-weight: 500;" id="passwordHelp1" class="form-text">Enter strong password for secure your account.</div>
                    </div>
                    <div class="mb-3">
                        <label for="password2" class="form-label">Repeat password</label>
                        <input type="password" class="form-control" name="repaeated_password" id="password2" aria-disabledby="passwordHelp2">
                        <div style="color: red; font-weight: 500;" id="passwordHelp2" class="form-text">Repeate your password.</div>
                    </div>

                </div>
                <div class="col col-lg-2">

                </div>
                <div class="col col-lg-5">
                    <h2 style="padding-left: 25%;">Car info</h2>
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



<jsp:include page="footer.jsp"/>

</body>
</html>
