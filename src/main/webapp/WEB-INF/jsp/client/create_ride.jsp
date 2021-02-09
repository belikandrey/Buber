<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 14:24
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
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU&amp;apikey=65907f2a-7637-445c-b787-974dcb41f5c2"
            type="text/javascript"></script>
    <script src="https://yandex.st/jquery/2.2.3/jquery.min.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="/resources/img/Buber_ico.ico"/>

    <style>
        html, body, #map {
            width: 100%;
            height: 100%;
            margin: 0;

        }
    </style>


    <link rel="stylesheet" type="text/css" href="/resources/css/common/home.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>



<div id="map" style="padding-top: 6%;  "></div>

<script type="text/javascript">
    var startLongitude, startLatitude;


    ymaps.ready(function () {
        var myMap = new ymaps.Map('map', {
            center: [55.753994, 37.622093],
            zoom: 15,
            // Добавим кнопку для построения маршрутов на карту.
            controls: ['routePanelControl']
        }), firstButton = new ymaps.control.Button({
            data: {content: "<p style='color:red; font-size:35px;'><fmt:message key='orderTaxi'/> </p>", title: "Order Taxi!"},
            options: {
                selectOnClick: false,
                maxWidth: 900,
                minWidth: 600,
                minHeight: 600,
                maxHeight: 450,
                position: {left: 150, bottom: 100}
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
            to = control.routePanel.state.get("to")
            ymaps.geocode(from).then(function (res) {
                textFrom = res.geoObjects.get(0).getAddressLine();
                ymaps.geocode(to).then(function (res) {
                    textTo = res.geoObjects.get(0).getAddressLine();
                    var multiRoutePromise = control.routePanel.getRouteAsync();
                    multiRoutePromise.then(function (multiRoute) {
                        var activeRoute = multiRoute.getActiveRoute();
                        if (activeRoute) {
                            // Вывод информации об активном маршруте.
                            distance = activeRoute.properties.get("distance").text;
                            const form = document.createElement('form');
                            form.method = 'post';
                            form.action = 'client?command=create_ride';
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








<jsp:include page="../common/footer.jsp"/>

</body>
</html>
