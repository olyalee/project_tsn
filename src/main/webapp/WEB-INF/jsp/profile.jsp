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
    <fmt:message var="edit" bundle="${profile}" key="profile.edit"/>
    <fmt:message var="saveChanges" bundle="${profile}" key="profile.save.changes"/>
    <fmt:message var="addNewEducation" bundle="${profile}" key="profile.add.new.education"/>
    <fmt:message var="deleteEducation" bundle="${profile}" key="profile.delete.education"/>
    <fmt:message var="addNew" bundle="${profile}" key="profile.add.new"/>
    <%--<jsp:useBean id="validation" class="com.teachers_social_network.web.servlet.model.FormValidation" scope="request"/>--%>
    <tags:menu title="${title}"/>
</head>
<body>
<%--<c:if test="${validation.errors.INVALID_PROFILE}">--%>
<%--<div class="alert alert-danger">--%>
<%--Invalid profile.--%>
<%--</div>--%>
<%--</c:if>--%>
<form action="profile" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <ul class="list-group">
                    <li class="list-group-item">
                        <div class="row" style="color:grey">
                            <div class="col-md-2"><label>${myProfile}</label></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${firstName}</label></div>
                            <div class="col-md-3"><label>${user.firstName}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="firstName"
                                                                              placeholder="${user.firstName}"
                                                                              value="${user.firstName}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${lastName}</label></div>
                            <div class="col-md-3"><label>${user.lastName}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="lastName"
                                                                              placeholder="${user.lastName}"
                                                                              value="${user.lastName}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${gender}</label></div>
                            <div class="col-md-3"><label>${user.gender}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="gender"
                                                                              placeholder="${user.gender}"
                                                                              value="${user.gender}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${birthDate}</label></div>
                            <div class="col-md-4"><label>${user.birthDate}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="birthDate"
                                                                              placeholder="${user.birthDate}"
                                                                              value="${user.birthDate}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${email}</label></div>
                            <div class="col-md-3"><label>${user.email}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="email"
                                                                              placeholder="${user.email}"
                                                                              value="${user.email}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${country}</label></div>
                            <div class="col-md-3"><label>${user.country}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="country"
                                                                              placeholder="${user.country}"
                                                                              value="${user.country}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${city}</label></div>
                            <div class="col-md-3"><label>${user.city}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="city"
                                                                              placeholder="${user.city}"
                                                                              value="${user.city}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${scienceField}</label></div>
                            <div class="col-md-3"><label>${user.science_field}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="scienceField"
                                                                              placeholder="${user.science_field}"
                                                                              value="${user.science_field}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${workingPlace}</label></div>
                            <div class="col-md-3"><label>${user.working_place}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="workingPlace"
                                                                              placeholder="${user.working_place}"
                                                                              value="${user.working_place}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-4"><label>${position}</label></div>
                            <div class="col-md-3"><label>${user.position}</label></div>
                            <div class="col-md-3" style="float: right"><input type="text" name="position"
                                                                              placeholder="${user.position}"
                                                                              value="${user.position}"></div>
                        </div>
                    </li>
                    <li class="list-group-item">
                        <div class="row">
                            <span class="col-md-9"></span>
                            <input type="hidden" name="editInfo">
                            <div class="col-md-1">
                                <input type="submit" class="btn btn-success btn-send" value="${saveChanges}">
                            </div>
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
                    <c:forEach var="education" items="${sessionScope.educationsList}">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${educationType}</label></div>
                                <div class="col-md-3"><label>${education.educationType}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="educationType"
                                                                                  placeholder="${education.educationType}"
                                                                                  value="${education.educationType}">
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${placeType}</label></div>
                                <div class="col-md-4"><label>${education.placeType}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="placeType"
                                                                                  placeholder="${education.placeType}"
                                                                                  value="${education.placeType}"></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${placeTitle}</label></div>
                                <div class="col-md-4"><label>${education.placeTitle}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="placeTitle"
                                                                                  placeholder="${education.placeTitle}"
                                                                                  value="${education.placeTitle}"></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${major}</label></div>
                                <div class="col-md-4"><label>${education.major}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="major"
                                                                                  placeholder="${education.major}"
                                                                                  value="${education.major}"></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${startYear}</label></div>
                                <div class="col-md-4"><label>${education.startYear}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="startYear"
                                                                                  placeholder="${education.startYear}"
                                                                                  value="${education.startYear}"></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${endYear}</label></div>
                                <div class="col-md-4"><label>${education.endYear}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="endYear"
                                                                                  placeholder="${education.endYear}"
                                                                                  value="${education.endYear}"></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <input type="hidden" name="edicationId" value="${education.id}">
                                <div class="col-md-9"><input type="submit" class="btn btn-danger btn-send"
                                                             value="${deleteEducation}" name="deleteEducation"></div>
                                <div class="col-md-1">
                                    <input type="submit" class="btn btn-success btn-send"
                                           value="${saveChanges}" name="saveChanges"></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row"></div>
                        </li>
                    </c:forEach>
                    <%--Block for adding a new education--%>
                    <c:if test="${requestScope.addNew!=null}">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${educationType}</label></div>
                                <div class="col-md-3"><label></label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="newEducationType"
                                                                                  placeholder="${educationType}"
                                                                                  required></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${placeType}</label></div>
                                <div class="col-md-4"><label>${education.placeType}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="newPlaceType"
                                                                                  placeholder="${placeType}"
                                                                                  required></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${placeTitle}</label></div>
                                <div class="col-md-4"><label>${education.placeTitle}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="newPlaceTitle"
                                                                                  placeholder="${placeTitle}"
                                                                                  required></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${major}</label></div>
                                <div class="col-md-4"><label>${education.major}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="newMajor"
                                                                                  placeholder="${major}"
                                                                                  required></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${startYear}</label></div>
                                <div class="col-md-4"><label>${startYear}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="newStartYear"
                                                                                  placeholder="${startYear}"
                                                                                  required></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-4"><label>${endYear}</label></div>
                                <div class="col-md-4"><label>${education.endYear}</label></div>
                                <div class="col-md-3" style="float: right"><input type="text" name="newEndYear"
                                                                                  placeholder="${education.endYear}"
                                                                                  required></div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <span class="col-md-9"></span>
                                <input type="hidden" name="newEdicationId" value="${education.id}">
                                <div class="col-md-1">
                                    <input type="submit" class="btn btn-success btn-send"
                                           value="${addNew}" name="addNewEducation">
                                </div>
                            </div>
                        </li>
                    </c:if>
                    <li class="list-group-item">
                        <div class="row">
                                <div class="col-md-2"><input type="submit" class="btn btn-info btn-send"
                                                             value="${addNewEducation}" name="addFormForNewEducation">
                                </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</form>
</body>
</html>