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
    <fmt:message var="commTitle" bundle="${profile}" key="communities.title"/>
    <tags:menu title="${title}"/>
</head>
<body>
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