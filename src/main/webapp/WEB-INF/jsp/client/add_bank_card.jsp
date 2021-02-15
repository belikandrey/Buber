<%--
  Created by IntelliJ IDEA.
  User: andreybelik
  Date: 7.02.21
  Time: 15:07
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
        <form action="client?command=add_bank_card" method="post">
            <div class="row">

                <div style="padding-left: 25%;" class="col col-lg-9">
                    <h2 style="padding-left: 30%;"><fmt:message key="cardNumber"/></h2>
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label"><fmt:message key="cardNumber"/> </label>
                        <input type="text"
                               pattern="^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|(?<mastercard>5[1-5][0-9]{14})|(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|(?<amex>3[47][0-9]{13})|(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$"
                               required="" class="form-control" id="cardNumber"
                               name="cardNumber" aria-describedby="card_numberHelp">
                        <div style="color: red; font-weight: 500;" id="card_numberHelp" class="form-text">
                            <fmt:message key="cardNumberHelp"/> (4255200053071799).
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="cardDate" class="form-label"><fmt:message key="cardDate"/> </label>
                        <input type="text" pattern="(?:0[1-9]|1[0-2])/[0-9]{2}" required class="form-control"
                               id="cardDate" name="cardDate"
                               aria-describedby="card_dateHelp">
                        <div style="color: red; font-weight: 500;" id="card_dateHelp" class="form-text"><fmt:message
                                key="cardDateHelp"/> (08/21, 11/23).
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="cardCsc" class="form-label"><fmt:message key="cardCsc"/> </label>
                        <input type="text" pattern="^\w{3}$" class="form-control" id="cardCsc" name="cardCsc"
                               aria-describedby="card_cscHelp">
                        <div style="color: red; font-weight: 500;" id="card_cscHelp" class="form-text"><fmt:message
                                key="cardCscHelp"/> (123, 435, 974).
                        </div>
                    </div>
                    <br>
                    <div class="row justify-content-center">
                        <div class="col-2">
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
            </div>
        </form>
    </div>

</section>


<jsp:include page="../common/footer.jsp"/>

</body>
</html>
