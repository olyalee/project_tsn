<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="i18n.root" var="root"/>
    <fmt:setBundle basename="i18n.profile" var="profile"/>
    <fmt:setBundle basename="i18n.messages" var="messages"/>
    <fmt:message var="title" bundle="${root}" key="root.messages"/>
    <fmt:message var="messagesFrom" bundle="${messages}" key="messages.from"/>
    <fmt:message var="messagesTo" bundle="${messages}" key="messages.to"/>
    <fmt:message var="messageText" bundle="${messages}" key="messages.text"/>
    <fmt:message var="messageTime" bundle="${messages}" key="messages.time"/>
    <fmt:message var="toEmpty" bundle="${messages}" key="messages.empty.to"/>
    <fmt:message var="textEmpty" bundle="${messages}" key="messages.empty.text"/>
    <fmt:message var="messageSend" bundle="${messages}" key="messages.send"/>
    <fmt:message var="messageWasSent" bundle="${messages}" key="messages.send.was"/>
    <fmt:message var="messageWasNotSent" bundle="${messages}" key="messages.send.wasnt"/>
    <tags:menu title="${title}"/>
</head>
<body>
<div class="container">
    <div class="row" style="color:green">
        <div class="col-md-10">
            <c:if test="${requestScope.wasSent!=null}">
                <c:choose>
                    <c:when test="${requestScope.wasSent}">
                        ${messageWasSent}<br>
                    </c:when>
                    <c:otherwise>
                        ${messageWasNotSent}<br>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>
</div>
<form id="send-message" action="messages" method="post">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="form-group">
                    <textarea id="form_message" name="messageText" class="form-control"
                              placeholder="${messageText}" rows="4" required="required"
                              data-error="${textEmpty}"></textarea>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="row" style="color:grey">
                <div class="col-md-2">
                    <div class="form-group">
                        <input id="form_toUser" name="toUser" class="form-control"
                               placeholder="${messagesTo}" required="required" data-error="${toEmpty}">
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <input type="hidden" name="send">
                        <input type="submit" class="btn btn-success btn-send" value="${messageSend}">
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
                        <div class="col-md-2"><label>${messagesFrom}</label></div>
                        <div class="col-md-1"><label>${messagesTo}</label></div>
                        <div class="col-md-6"><label>${messageText}</label></div>
                        <div class="col-md-3"><label>${messageTime}</label></div>
                    </div>
                </li>
                <c:forEach var="message" items="${sessionScope.messagesList}">
                    <li class="list-group-item">
                        <div class="row">
                            <div class="col-md-2"><label>${message.from_user}</label></div>
                            <div class="col-md-1"><label>${message.to_user}</label></div>
                            <div class="col-md-6"><label>${message.text}</label></div>
                            <div class="col-md-3"><label>${message.time}</label></div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>