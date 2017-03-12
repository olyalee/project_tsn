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
    <fmt:message var="login" bundle="${auto}" key="auto.login"/>
    <fmt:message var="addColleague" bundle="${colleagues}" key="colleagues.add.colleague"/>
    <fmt:message var="removeColleague" bundle="${colleagues}" key="colleagues.remove.colleague"/>
    <fmt:message var="colleagueLogin" bundle="${colleagues}" key="colleagues.login"/>
    <fmt:message var="colleagueWasAdded" bundle="${colleagues}" key="colleagues.add.was"/>
    <fmt:message var="colleagueWasNotAdded" bundle="${colleagues}" key="colleagues.add.wasnt"/>
    <fmt:message var="colleagueWasRemoved" bundle="${colleagues}" key="colleagues.remove.was"/>
    <fmt:message var="colleagueWasNotRemoved" bundle="${colleagues}" key="colleagues.remove.wasnt"/>
    <tags:menu title="${title}"/>
</head>
<body>
<div class="container">
    <div class="row" style="color:green">
        <div class="col-md-10">
            <c:if test="${requestScope.wasAdded!=null}">
                <c:choose>
                    <c:when test="${requestScope.wasAdded}">
                        ${colleagueWasAdded}
                    </c:when>
                    <c:otherwise>
                        ${colleagueWasNotAdded}
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${requestScope.wasRemoved!=null}">
                <c:choose>
                    <c:when test="${requestScope.wasRemoved}">
                        ${colleagueWasRemoved}
                    </c:when>
                    <c:otherwise>
                        ${colleagueWasNotRemoved}
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
</div>
<form id="add-colleague" action="colleagues" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="row" style="color:grey">
                    <div class="col-md-3">
                        <div class="form-group">
                            <input id="colleagueToAdd" name="colleagueToAdd" class="form-control"
                                   placeholder="${colleagueLogin}" required="required">
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <input type="hidden" name="addColleague">
                            <input type="submit" class="btn btn-success btn-send" value="${addColleague}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<form id="remove-colleague" action="colleagues" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="row" style="color:grey">
                    <div class="col-md-3">
                        <div class="form-group">
                            <input id="removeColleague" name="colleagueToRemove" class="form-control"
                                   placeholder="${colleagueLogin}" required="required">
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <input type="hidden" name="removeColleague">
                            <input type="submit" class="btn btn-danger btn-send" value="${removeColleague}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="container">
    <div class="row">
        <div class="col-md-10">
            <ul class="list-group">
                <li class="list-group-item">
                    <div class="row" style="color:grey">
                        <div class="col-md-3"><label>${myColleagues}</label></div>
                        <div class="col-md-2"><label>${login}</label></div>
                        <div class="col-md-2"><label>${scienceField}</label></div>
                        <div class="col-md-3"><label>${workingPlace}</label></div>
                    </div>
                </li>
                <c:forEach var="colleague" items="${sessionScope.colleaguesList}">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-3"><label>${colleague.firstName} ${colleague.lastName}</label></div>
                            <div class="col-md-2"><label>${colleague.login}</label></div>
                            <div class="col-md-2"><label>${colleague.science_field}</label></div>
                            <div class="col-md-2"><label>${colleague.working_place}</label></div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>