<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 12.01.21
  Time: 00:23
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
    <title><fmt:message key="createRide.title"/> </title>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=65907f2a-7637-445c-b787-974dcb41f5c2"
            type="text/javascript"></script>
    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>

    <style>
        html, body, #map {
            width: 100%;
            height: 100%;
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="width: 100%; height: 85%" id="map"></div>

<script type="text/javascript">
    var startLongitude, startLatitude;


    ymaps.ready(function () {
        var myMap = new ymaps.Map('map', {
            center: [55.753994, 37.622093],
            zoom: 15,
            // Добавим кнопку для построения маршрутов на карту.
            controls: ['routePanelControl']
        }), firstButton = new ymaps.control.Button({
            data: {content: "Order Taxi!", title: "Order Taxi!"},
            options: {
                selectOnClick: false,
                maxWidth: 300,
                maxHeight: 250,
                minWidth: 250,
                minHeight: 200,
                position: {right: 70, bottom: 50}
            }
        });

        myMap.controls.add(firstButton);
        var control = myMap.controls.get('routePanelControl');
        ymaps.geolocation.get({
            provider: 'browser'
        }).then(function (result) {
            startLongitude = result.geoObjects.get(0).properties.get('boundedBy')[1][0];
            startLatitude = result.geoObjects.get(0).properties.get('boundedBy')[1][1];
        });

        control.routePanel.options.set({
            allowSwitch: true,
            // Зададим виды маршрутизации, которые будут доступны пользователям для выбора.
            types: {auto: true}
        });
        // Зададим координаты пункта отправления с помощью геолокации.
        control.routePanel.geolocate('from');
        var from, to, distance, textFrom, textTo;
        firstButton.events.add('click', function () {
            from = control.routePanel.state.get("from");
            console.log("From x : " + from[0]);
            console.log("From y : " + from[1]);
            to = control.routePanel.state.get("to")
            console.log("To x : " + to[0]);
            console.log("To y : " + to[1]);
            ymaps.geocode(from).then(function (res) {
                textFrom = res.geoObjects.get(0).getAddressLine();
                console.log("textFrom : " + textFrom);
                ymaps.geocode(to).then(function (res) {
                    textTo = res.geoObjects.get(0).getAddressLine();
                    console.log("textTo : " + textTo);
                    var multiRoutePromise = control.routePanel.getRouteAsync();
                    multiRoutePromise.then(function (multiRoute) {
                        var activeRoute = multiRoute.getActiveRoute();
                        if (activeRoute) {
                            // Вывод информации об активном маршруте.
                            distance = activeRoute.properties.get("distance").text;
                            console.log("Длина: " + distance);
                            console.log("Время прохождения: " + activeRoute.properties.get("duration").text);
                            const form = document.createElement('form');
                            form.method = 'post';
                            form.action = 'client?command=to_create_ride_submit';
                            form.acceptCharset = 'utf8';
                            params = {
                                text_from: textFrom,
                                text_to: textTo,
                                from_x: from[0],
                                from_y: from[1],
                                to_x: to[0],
                                to_y: to[1],
                                dist: distance
                            }
                            for (const key in params) {
                                if (params.hasOwnProperty(key)) {
                                    const hiddenField = document.createElement('input');
                                    hiddenField.type = 'hidden';
                                    hiddenField.name = key;
                                    hiddenField.value = params[key];
                                    form.appendChild(hiddenField);
                                }
                            }
                            document.body.appendChild(form);
                            form.submit();
                        }
                    });
                });
            });
            //!!!!!!!!!
        });
        // Откроем панель для построения маршрутов.
        control.state.set('expanded', true);
    });
</script>
</body>
</html>
