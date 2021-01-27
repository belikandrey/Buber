<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 23.01.21
  Time: 11:46
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
    <title><fmt:message key="submitRide"/></title>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=65907f2a-7637-445c-b787-974dcb41f5c2"
            type="text/javascript"></script>
    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1><fmt:message key="submitRide"/> : </h1>
<div style="width: 100%; height: 85%" id="map"></div>
<form method="post" action="driver?command=to_end_driver_ride">
    <p><fmt:message key="enterCar"/> : <select id="driverCar" name="driverCar">
        <c:forEach var="car" items="${cars}">
            <option value="${car.id}">${car.number}</option>
        </c:forEach>
    </select></p>
    <input type="hidden" name="ride_id" value="${ride.id}">
    <p id="startLocation" hidden>${ride.startLocation.address}</p>
    <p>
        <button type="submit"><fmt:message key="startRide"/> </button>
    </p>
    <p>${message}</p>
</form>
<script type="text/javascript">
    var element = document.getElementById('startLocation');
    var text = element.textContent;
    console.log("Text : " + text);

    ymaps.ready(function () {
        var myMap = new ymaps.Map('map', {
            center: [55.753994, 37.622093],
            zoom: 9,
            // Добавим кнопку для построения маршрутов на карту.
            controls: ['routeButtonControl']
        });

        var control = myMap.controls.get('routeButtonControl');

        // Зададим координаты пункта отправления с помощью геолокации.
        control.routePanel.geolocate('from');
        control.routePanel.state.set({
            to: text
        })
        // Откроем панель для построения маршрутов.
        control.state.set('expanded', true);
    });
</script>
</body>
</html>
