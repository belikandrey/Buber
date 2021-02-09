<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 17:57
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

</head>
<body>
<jsp:include page="header.jsp"/>


<div id="map" style="padding-top: 1%;  "></div>
<p id="startLocation" hidden>${ride.startLocation.address}</p>
<script type="text/javascript">
    var element = document.getElementById('startLocation');
    var text = element.textContent;

    ymaps.ready(function () {
        var myMap = new ymaps.Map('map', {
            center: [55.753994, 37.622093],
            zoom: 9,
            // Добавим кнопку для построения маршрутов на карту.
            controls: ['routeButtonControl']
        }),  firstButton = new ymaps.control.Button({
            data: {content: "<p style='color:red; font-size:35px;'>Start ride!</p>", title: "Start!"},
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
        var control = myMap.controls.get('routeButtonControl');

        // Зададим координаты пункта отправления с помощью геолокации.
        control.routePanel.geolocate('from');
        control.routePanel.state.set({
            to: text
        })
        // Откроем панель для построения маршрутов.
        control.state.set('expanded', true);
        firstButton.events.add('click', function () {
            const form = document.createElement('form');
            form.method = 'post';
            form.action = 'driver?command=to_end_ride';
            form.acceptCharset = 'utf8';
            params = {
                ride_id:${ride.id}
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
        });

    });
</script>

<jsp:include page="../common/footer.jsp"/>


</body>
</html>
