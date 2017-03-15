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
    <fmt:message var="education" bundle="${profile}" key="profile.education"/>
    <fmt:message var="myProfile" bundle="${profile}" key="profile.my.profile"/>
    <fmt:message var="otherProfile" bundle="${profile}" key="profile.other.profile"/>
    <fmt:message var="edit" bundle="${profile}" key="profile.edit"/>
    <fmt:message var="saveChanges" bundle="${profile}" key="profile.save.changes"/>
    <fmt:message var="addNewEducation" bundle="${profile}" key="profile.add.new.education"/>
    <fmt:message var="deleteEducation" bundle="${profile}" key="profile.delete.education"/>
    <fmt:message var="addNew" bundle="${profile}" key="profile.add.new"/>
    <tags:menu title="${title}"/>
</head>
<body>
<%--<c:set var="colleague" scope="request" value="colleague"/>--%>
<%--<c:set var="colleagueEducationList" scope="request" value="colleagueEducationList"/>--%>
<div class="container">
    <div class="row">
        <div class="col-md-10">
            <ul class="list-group">
                <li class="list-group-item">
                    <div class="row" style="color:grey">
                        <div class="col-md-10">
                            <label>${otherProfile}: ${colleague.firstName} ${colleague.lastName}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4" ><label>${firstName}</label></div>
                        <div class="col-md-3" ><label>${colleague.firstName}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${lastName}</label></div>
                        <div class="col-md-3" ><label>${colleague.lastName}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${gender}</label></div>
                        <div class="col-md-3" ><label>${colleague.gender}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${birthDate}</label></div>
                        <div class="col-md-4" ><label>${colleague.birthDate}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${email}</label></div>
                        <div class="col-md-3" ><label>${colleague.email}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${country}</label></div>
                        <div class="col-md-3" ><label>${colleague.country}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${city}</label></div>
                        <div class="col-md-3" ><label>${colleague.city}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${scienceField}</label></div>
                        <div class="col-md-3" ><label>${colleague.science_field}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${workingPlace}</label></div>
                        <div class="col-md-3" ><label>${colleague.working_place}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-4"><label>${position}</label></div>
                        <div class="col-md-3" ><label>${colleague.position}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row"></div>
                </li>
                <%--Education block--%>
                <li class="list-group-item">
                    <div class="row" style="color:grey">
                        <div class="col-md-10"><label>${education}</label></div>
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="row">
                    </div>
                </li>
                <c:forEach var="education" items="${colleagueEducationList}">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${educationType}</label></div>
                            <div class="col-md-4" ><label>${education.educationType}</label></div>
                        </div>

                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${placeType}</label></div>
                            <div class="col-md-4" ><label>${education.placeType}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${placeTitle}</label></div>
                            <div class="col-md-4" ><label>${education.placeTitle}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${major}</label></div>
                            <div class="col-md-4" ><label>${education.major}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${startYear}</label></div>
                            <div class="col-md-4" ><label>${education.startYear}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${endYear}</label></div>
                            <div class="col-md-4" ><label>${education.endYear}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row"></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>