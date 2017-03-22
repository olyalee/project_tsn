<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="i18n.root" var="root"/>
    <fmt:message var="title" bundle="${root}" key="root.error"/>
    <tags:menu title="${title}"/>
</head>
<body>
<fmt:message var="error" bundle="${root}" key="root.error.text"/>
<div align="center"> ${error}</div>
</body>
</html>