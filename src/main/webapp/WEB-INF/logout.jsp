<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="i18n.root" var="root"/>
    <fmt:setBundle basename="i18n.login" var="login"/>
    <fmt:message var="title" bundle="${root}" key="root.title"/>
    <tags:menu title="${title}"/>
</head>
<body>
<fmt:message var="logout" bundle="${login}" key="login.logout"/>
<div align="center"> ${logout}</div>
</body>
</html>
