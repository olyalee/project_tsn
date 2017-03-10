<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="i18n.root" var="root"/>
    <fmt:setBundle basename="i18n.autorization" var="auto"/>
    <fmt:setBundle basename="i18n.profile" var="profile"/>
    <fmt:setBundle basename="i18n.colleagues" var="colleagues"/>
    <fmt:message var="title" bundle="${root}" key="root.colleagues"/>
    <fmt:message var="myColleagues" bundle="${colleagues}" key="colleagues.my.colleagues"/>
    <fmt:message var="scienceField" bundle="${profile}" key="profile.science.field"/>
    <fmt:message var="workingPlace" bundle="${profile}" key="profile.working.place"/>
    <tags:menu title="${title}"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-10">
            <ul class="list-group">
                <li class="list-group-item">
                    <div class="row" style="color:grey">
                        <div class="col-md-4"><label>${myColleagues}</label></div>
                        <div class="col-md-4"><label>${scienceField}</label></div>
                        <div class="col-md-4"><label>${workingPlace}</label></div>
                    </div>
                </li>
                <c:forEach var="colleague" items="${sessionScope.colleaguesList}">
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${colleague.firstName} ${colleague.lastName}</label></div>
                        <div class="col-md-4"><label>${colleague.science_field}</label></div>
                        <div class="col-md-4"><label>${colleague.working_place}</label></div>
                    </div>
                </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class=""
    </div>
</div>
</body>
</html>