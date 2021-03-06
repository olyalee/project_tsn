<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="i18n.root" var="root"/>
    <fmt:setBundle basename="i18n.autorization" var="auto"/>
    <fmt:message var="title" bundle="${root}" key="root.title"/>
    <tags:menu title="${title}"/>
</head>
<body>
<div class="container">
    <h2><fmt:message bundle="${root}" key="root.title"/></h2>
</div>
<c:if test="${sessionScope.user==null}">
    <div class="container">
        <div class="row">
            <div class="col-md-6 well"><h3><fmt:message bundle="${auto}" key="auto.signin"/></h3>
                <form id="loginform" class="form-inline" role="form" action="login" method="post">
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.login"/></label>
                        <input type="text" id="inputLogin" name="inputLogin" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.login"/>" required
                               autofocus/>
                    </div>
                    <div class="form-group">
                    <label class="sr-only"><fmt:message bundle="${auto}" key="auto.password"/></label>
                    <input type="password" id="inputPassword" name="inputPassword" class="form-control"
                           placeholder="<fmt:message bundle="${auto}" key="auto.password"/>" required>
                    </div>
                    <button type="submit" class="btn btn-success"><fmt:message bundle="${auto}"
                                                                               key="auto.signin"/></button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 well"><h3><fmt:message bundle="${auto}" key="auto.register"/></h3>
                <form id="regform" class="form-inline" role="form" action="registration" method="post">
                    <div class="form-group">
                            <label class="sr-only"><fmt:message bundle="${auto}" key="auto.login"/></label>
                        <input type="text" id="newLogin" name="newLogin" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.login"/>" required autofocus/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.password"/></label>
                        <input type="password" id="newPassword" name="newPassword" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.password"/>" required></div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.firstname"/></label>
                        <input type="text" id="newFirstname" name="newFirstname" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.firstname"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.lastname"/></label>
                        <input type="text" id="newLastname" name="newLastname" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.lastname"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.gender"/></label>
                        <input type="text" id="newGender" name="newGender" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.gender"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.birthdate"/></label>
                        <input type="text" id="newBirthDate" name="newBirthDate" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.birthdate"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.email"/></label>
                        <input type="email" id="newEmail" name="newEmail" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.email"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.country"/></label>
                        <input type="text" id="newCountry" name="newCountry" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.country"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.city"/></label>
                        <input type="text" id="newCity" name="newCity" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.city"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.sciencefield"/></label>
                        <input type="text" id="newScienceField" name="newScienceField" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.sciencefield"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.place"/></label>
                        <input type="text" id="newPlace" name="newPlace" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.place"/>" required/>
                    </div>
                    <div class="form-group">
                        <label class="sr-only"><fmt:message bundle="${auto}" key="auto.position"/></label>
                        <input type="text" id="newPosition" name="newPosition" class="form-control"
                               placeholder="<fmt:message bundle="${auto}" key="auto.position"/>" required/>
                    </div>
                    <button type="submit" class="btn btn-success"><fmt:message bundle="${auto}"
                                                                               key="auto.register"/></button>
                </form>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
