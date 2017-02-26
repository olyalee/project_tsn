<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value ="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.login" var="loc"/>
<html>
    <head>
        <title><fmt:message bundle="${loc}" key="login.title"/></title>
    </head>
    <body>
        <h2><fmt:message bundle="${loc}" key="login.title"/></h2>
        <form class="login" action="login" method="post">
            <table>
                <tbody>
                    <tr>
                        <td><label for="name"><fmt:message bundle="${loc}" key="login.name"/></label></td>
                        <td><input id="name" type="text" name="login"/></td>
                    </tr>
                      <tr>
                        <td><label for="pwd"><fmt:message bundle="${loc}" key="login.password"/></label></td>
                        <td><input id="pwd" type="password" name="password"/></td>
                    </tr>
                </tbody>
            </table>
                        <fmt:message bundle="${loc}" key="login.log.in" var="log_in"/>
                        <input type ="submit" value="${do_login}"/>
        </form>
    </body>
</html>