<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 15:33
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
<jsp:include page="header.jsp"/>


<section class="taxi-background">
    <div class="container">
        <div class="row">
            <!--STATUS-->
            <h2 class="big-font middle-left">Current phone number : ${client.phoneNumber}</h2>
        </div>
    </div>
    <div class="container">
        <form action="client?command=update_client_phone" method="post">
            <div class="row">

                <div style="padding-left: 25%;" class="col col-lg-9">
                    <h2 style="padding-left: 30%;">New phone number</h2>
                    <div class="mb-3">
                        <label for="newPhoneNumber" class="form-label">Phone number</label>
                        <input type="text" class="form-control" id="newPhoneNumber" name="newPhoneNumber"
                               aria-describedby="phone_numberHelp">
                        <div style="color: red; font-weight: 500;" id="phone_numberHelp" class="form-text">Enter phone
                            number(+375333274120).
                        </div>
                    </div>
                    <div style="margin-top: 4%;margin-left: 30%;" class="row">
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
