<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 6.02.21
  Time: 17:18
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
            <h2 class="big-font" >Welcome to the Buber!<br><br>Have an account?</h2>
            <form action="home?command=log_in" method="post">
                <div class="mb-3">
                    <label for="login" class="form-label">Login</label>
                    <input type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp">
                    <div style="color: red; font-weight: 500;" id="loginHelp" class="form-text">We'll never share your login with anyone else.</div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" id="exampleInputPassword1">
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="role" id="flexRadioClient" checked value="client">
                    <label class="form-check-label" for="flexRadioClient">
                        Client
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="role" id="flexRadioDriver" value="driver" >
                    <label class="form-check-label" for="flexRadioDriver">
                        Driver
                    </label>
                </div>
                <button type="submit" class="btn btn-secondary">Submit</button>
                <p style="color: red; font-weight: bold;">${message}</p>
            </form>
            <div style="padding-top: 25px;"><a style="color: red;" href="home?command=to_forgot_password">Forgot password?</a></div>
        </div>
        <div class="row" >
            <h2 class="big-font" style="color:red;" >Haven't an account?</h2>
            <div class="button-wrapper">
                <br>

                <a href="home?command=to_client_sign_in"><button style="left: 5%;" type="button" class="btn btn-secondary">Client</button></a>

                <a href="home?command=to_driver_sign_in"><button style="left: 34%;" type="button" class="btn btn-secondary">Driver</button></a>
            </div>
        </div>
    </div>

</section>







<jsp:include page="footer.jsp"/>
</body>
</html>
