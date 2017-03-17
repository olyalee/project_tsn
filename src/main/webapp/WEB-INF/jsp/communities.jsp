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
    <fmt:setBundle basename="i18n.communities" var="communities"/>
    <fmt:message var="title" bundle="${root}" key="root.communities"/>
    <fmt:message var="myCommunities" bundle="${communities}" key="communities.my.communities"/>
    <fmt:message var="communityWasAdded" bundle="${communities}" key="commmunities.added.was"/>
    <fmt:message var="communityWasNotAdded" bundle="${communities}" key="communities.added.wasnt"/>
    <fmt:message var="communityWasRemoved" bundle="${communities}" key="communities.removed.was"/>
    <fmt:message var="communityWasNotRemoved" bundle="${communities}" key="communities.removed.wasnt"/>
    <fmt:message var="addCommunity" bundle="${communities}" key="communities.add.community"/>
    <fmt:message var="removeCommunity" bundle="${communities}" key="communities.remove.community"/>
    <fmt:message var="communityTitle" bundle="${communities}" key="communities.title"/>
    <tags:menu title="${title}"/>
</head>
<body>
<div class="container">
    <div class="row" style="color:green">
        <div class="col-md-10">
            <c:if test="${requestScope.wasAdded!=null}">
                <c:choose>
                    <c:when test="${requestScope.wasAdded}">
                        ${communityWasAdded}
                    </c:when>
                    <c:otherwise>
                        ${communityWasNotAdded}
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${requestScope.wasRemoved!=null}">
                <c:choose>
                    <c:when test="${requestScope.wasRemoved}">
                        ${communityWasRemoved}
                    </c:when>
                    <c:otherwise>
                        ${communityWasNotRemoved}
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
</div>
<form id="add-community" action="communities" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="row" style="color:grey">
                    <div class="col-md-3">
                        <div class="form-group">
                            <input id="communityToAdd" name="communityToAdd" class="form-control"
                                   placeholder="${communityTitle}" required="required">
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <input type="hidden" name="addCommunity">
                            <input type="submit" class="btn btn-success btn-send" value="${addCommunity}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<form id="remove-communtiy" action="communities" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="row" style="color:grey">
                    <div class="col-md-3">
                        <div class="form-group">
                            <input id="removeCommunity" name="communityToRemove" class="form-control"
                                   placeholder="${communityTitle}" required="required">
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <input type="hidden" name="removeCommunity">
                            <input type="submit" class="btn btn-danger btn-send" value="${removeCommunity}">
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
                        <div class="col-md-6"><label>${myCommunities}</label></div>
                    </div>
                </li>
                <c:forEach var="community" items="${sessionScope.communitiesList}">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-12"><label>${community.title}</label></div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>