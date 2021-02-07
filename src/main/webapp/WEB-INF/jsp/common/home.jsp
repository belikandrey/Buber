<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 28.12.20
  Time: 22:45
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
        <div class="row">
            <h1 class="big-font" >Welcome to the Buber!<br><br><br><p><a class="biggest-text" style="color: black;" href="tel:#">666-66-66</a></p></h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <h2 style="text-align: center; padding-top: 5%;">CAR TYPES</h2>
        <div class="divider divider-default"></div>
        <div class="row colomns-padd">
            <div class="col">

                <div class="box-image">
                    <img src="/resources/img/car_type1.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i>BASIC</i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li>Lots of locations</li>
                            <li>Waiting over 12 min</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="box-image">
                    <img src="/resources/img/car_type2.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i>BUSINES</i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li>Lots of locations</li>
                            <li>Waiting over 12 min</li>
                            <li>Luggage transportation</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="box-image">
                    <img src="/resources/img/car_type3.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i>MINIVAN</i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li>Lots of locations</li>
                            <li>Waiting over 12 min</li>
                            <li>Luggage transportation</li>
                            <li>Baby seat</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="box-image">
                    <img src="/resources/img/car_type4.png">
                </div>
                <div class="box-info">
                    <div class="box-header">
                        <h4><i>TRUCK</i></h4>
                    </div>
                    <div class="box-pluses">
                        <ul class="box-list">
                            <li>Lots of locations</li>
                            <li>Waiting over 12 min</li>
                            <li>Luggage transportation</li>
                            <li>Baby seat</li>
                            <li>Animal transportation</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="bg-grey">
    <div class="container quest">
        <div class="row">
            <div class="col">
                <div class="img-wrapper">
                    <img src="/resources/img/questions.jpg">
                </div>
            </div>
            <div class="col">
                <div class="questions">
                    <h2>COMMON QUESTIONS</h2>
                    <br>
                    <p class="quetions-info">
                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="false" aria-controls="collapse1">
                            How can I order a taxi?
                        </button>
                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2" aria-expanded="false" aria-controls="collapse2e">
                            How can I pay for my order in the taxi?
                        </button>
                    </p>
                    <div class="collapse" id="collapse1">
                        <div class="card card-body">
                            Ordering our taxi is easy! Since you are already here you can book your trip now using our website.  Ordering a taxi online saves you time. You should sign up in our website and click on button "Create order".
                        </div>
                    </div>
                    <div class="collapse" id="collapse2">
                        <div class="card card-body">
                            We accept cash and major credit cards - Visa, MasterCard, and Maestro.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
