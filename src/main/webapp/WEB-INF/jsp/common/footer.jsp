<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>


<footer id="foot" class="section footer-classic context-dark bg-image" style="background: #2d3246;">
    <div class="container">
        <div class="row row-30">
            <div class="col-md-4 col-xl-7">
                <div class="pr-xl-4"><a class="brand" href="#"><img class="brand-logo-light" src="/resources/img/footer_img.jpg" alt="" width="125" height="75" ></a>
                    <p><fmt:message key="footerAbotAwards"/> </p>
                    <!-- Rights-->
                    <p class="rights"><span>@ </span><span class="copyright-year">2021</span><span> </span><span>Buber</span><span>. </span><span><fmt:message key="allRightsReserved"/> </span></p>
                </div>
            </div>
            <div class="col-md-4">
                <h5><fmt:message key="contacts"/> </h5>
                <dl class="contact-list">
                    <dt><fmt:message key="address"/> :</dt>
                    <dd><fmt:message key="addressDefacto"/> </dd>
                </dl>
                <dl class="contact-list">
                    <dt>Email:</dt>
                    <dd><a href="mailto:#">BuberOrg@gmail.com</a></dd>
                </dl>
                <dl class="contact-list">
                    <dt><fmt:message key="phones"/> :</dt>
                    <dd><a href="tel:#">+375296666666</a> <span> </span> <a href="tel:#">+375256666666</a>
                    </dd>
                </dl>
            </div>
        </div>
    </div>
</footer>

