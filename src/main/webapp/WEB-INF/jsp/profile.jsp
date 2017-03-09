<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.teachers_social_network.model.User" import="com.teachers_social_network.model.Education"%>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="i18n.root" var="root"/>
    <fmt:setBundle basename="i18n.autorization" var="auto"/>
    <fmt:setBundle basename="i18n.profile" var="profile"/>
    <fmt:message var="title" bundle="${profile}" key="profile.title"/>
    <fmt:message var="firstName" bundle="${profile}" key="profile.first.name"/>
    <fmt:message var="lastName" bundle="${profile}" key="profile.last.name"/>
    <fmt:message var="gender" bundle="${profile}" key="profile.gender"/>
    <fmt:message var="birthDate" bundle="${profile}" key="profile.birth.date"/>
    <fmt:message var="email" bundle="${profile}" key="profile.email"/>
    <fmt:message var="country" bundle="${profile}" key="profile.country"/>
    <fmt:message var="city" bundle="${profile}" key="profile.city"/>
    <fmt:message var="scienceField" bundle="${profile}" key="profile.science.field"/>
    <fmt:message var="workingPlace" bundle="${profile}" key="profile.working.place"/>
    <fmt:message var="position" bundle="${profile}" key="profile.position"/>
    <fmt:message var="educationType" bundle="${profile}" key="profile.education.type"/>
    <fmt:message var="placeType" bundle="${profile}" key="profile.place.type"/>
    <fmt:message var="placeTitle" bundle="${profile}" key="profile.place.title"/>
    <fmt:message var="major" bundle="${profile}" key="profile.major"/>
    <fmt:message var="startYear" bundle="${profile}" key="profile.start.year"/>
    <fmt:message var="endYear" bundle="${profile}" key="profile.end.year"/>
    <jsp:useBean id="validation" class="com.teachers_social_network.web.servlet.model.FormValidation" scope="request"/>
    <jsp:useBean id="profile" type="com.teachers_social_network.web.servlet.model.Profile" scope="request"/>
    <tags:menu title="${title}"/>
</head>
<body>
    <c:if test="${validation.errors.INVALID_PROFILE}">
        <div class="alert alert-danger">
            Invalid profile.
        </div>
    </c:if>
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <ul class="list-group">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${firstName}</label></div>
                            <div class="col-md-4"><label>${user.firstName}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${lastName}</label></div>
                            <div class="col-md-4"><label>${user.lastName}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${gender}</label></div>
                            <div class="col-md-4"><label>${user.gender}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${birthDate}</label></div>
                            <div class="col-md-4"><label>${user.birthDate}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${email}</label></div>
                            <div class="col-md-4"><label>${user.email}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${country}</label></div>
                            <div class="col-md-4"><label>${user.country}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${city}</label></div>
                            <div class="col-md-4"><label>${user.city}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${scienceField}</label></div>
                            <div class="col-md-4"><label>${user.science_field}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${workingPlace}</label></div>
                            <div class="col-md-4"><label>${user.working_place}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${position}</label></div>
                            <div class="col-md-4"><label>${user.position}</label></div>
                        </div>
                    </li>
                    <c:forEach var="education" items="${sessionScope.educationsList}">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${educationType}</label></div>
                                <div class="col-md-4"><label>${education.educationType}</label></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${placeType}</label></div>
                                <div class="col-md-4"><label>${education.placeType}</label></div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
