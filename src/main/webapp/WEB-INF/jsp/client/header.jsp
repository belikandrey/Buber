<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 6.02.21
  Time: 18:01
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
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/home.css"/>">
<link rel="shortcut icon" href="Buber_ico.ico">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<div class="header">
    <nav class="navbar fixed-top navbar-expand-lg navbar-light" style="background-color: white;">
        <div class="container">
            <nav class="navbar navbar-light bg-light">
                <div class="container">
                    <a class="navbar-brand" href="client?command=to_client_home">
                        <img src="/resources/img/headerTaxi.png" alt="" width="35" height="35">
                    </a>
                </div>
            </nav>
            <a class="navbar-brand" href="client?command=to_client_home">Buber</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <!--<ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <form>
                        <select style="width: 100%;margin-top: 30%;" class="btn-light" id="language" name="language"
                                onchange="submit()">
                            <option value="" selected disabled hidden>Lang</option>
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Ru</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
                        </select>
                    </form>
                    </li>
                    <li class="nav-item" style="margin-top: 3%;">
                        <a class="nav-link" href="#foot">Contacts</a>
                    </li>
                </ul>-->
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item">
                        <a class="nav-link" href="#foot">Contacts</a>
                    </li>
                </ul>

                <a href="client?command=logout"><button  class="btn btn-success" type="submit">Logout</button></a>

            </div>

        </div>
    </nav>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>


</header>