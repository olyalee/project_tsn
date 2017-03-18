<%@attribute name="title" rtexprvalue="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.autorization" var="auto"/>
<fmt:setBundle basename="i18n.login" var="login"/>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:url var="bsMain" value="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${bsMain}"/>
<c:url var="bsTheme" value="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" href="${bsTheme}"/>
<c:url var="bsFlag" value="/webjars/flag-icon-css/2.4.0/css/flag-icon.min.css"/>
<link rel="stylesheet" href="${bsFlag}"/>
<title>${title}</title>

<nav class="navbar navbar-inverse bg-primary">
    <div class="container">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/TSN"><fmt:message bundle="${root}" key="root.home"/></a></li>
                <c:url var="profileUrl" value="/jsp/profile"/>
                <li><a href="${profileUrl}"><fmt:message bundle="${root}" key="root.profile"/></a></li>
                <c:url var="colleaguesUrl" value="/jsp/colleagues"/>
                <li><a href="${colleaguesUrl}"><fmt:message bundle="${root}" key="root.colleagues"/></a></li>
                <c:url var="communitiesUrl" value="/jsp/communities"/>
                <li><a href="${communitiesUrl}"><fmt:message bundle="${root}" key="root.communities"/></a></li>
                <%--<c:url var="messagesUrl" value="/jsp/messages"/>--%>
                <c:url var="messagesUrl" value="/jsp/messages"/>
                <li><a href="${messagesUrl}"><fmt:message bundle="${root}" key="root.messages"/></a></li>

            </ul>

            <c:if test="${not empty sessionScope.user}">
                <c:url var="logoutUrl" value="/jsp/logout"/>
                <form class="navbar-form navbar-right" action="${logoutUrl}" method="get">
                    <fmt:message var="btnLogOut" bundle="${login}" key="login.log.out"/>
                    <button type="submit" class="btn btn-default" title="${btnLogOut}">${btnLogOut}</button>
                </form>
            </c:if>

            <c:set var="currentUrl"
                   value="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}"/>
            <c:url var="localeUrl" value="/locale"/>
            <form class="navbar-form navbar-right" action="${localeUrl}" method="post">
                <input type="hidden" name="locale" value="en"/>
                <input type="hidden" name="redirect_to" value="${currentUrl}"/>
                <div class="form-group">
                    <fmt:message var="enTitle" bundle="${auto}" key="auto.select.en"/>
                    <button type="submit" class="btn flag-icon flag-icon-us" title="${enTitle}"></button>
                </div>
            </form>
            <c:url var="localeUrl" value="/locale"/>
            <form class="navbar-form navbar-right" action="${localeUrl}" method="post">
                <input type="hidden" name="locale" value="ru"/>
                <input type="hidden" name="redirect_to" value="${currentUrl}"/>
                <div class="form-group">
                    <fmt:message var="ruTitle" bundle="${auto}" key="auto.select.ru"/>
                    <button type="submit" class="btn flag-icon flag-icon-ru" title="${ruTitle}"></button>
                </div>
            </form>
        </div>

    </div>
</nav>
