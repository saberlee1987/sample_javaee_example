<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Page</title>
</head>
<%--@elvariable id="message" type="java.lang.String"--%>
<body>
<h4>Hello Page</h4>
<h3>
    <c:if test="${message!=null}">
        <c:out value="${message}"/>
    </c:if>
</h3>

<form action="${pageContext.request.contextPath}/hello" method="get">
    <input type="hidden" name="action" value="sayHello">
    <label for="firstName">firstName : </label>
    <input id="firstName" name="firstName" size="15" placeholder="Enter the firstName"/> <br><br>
    <label for="lastName">lastName : </label>
    <input id="lastName" name="lastName" size="15" placeholder="Enter the lastName"/> <br><br>
    <input type="submit" value="Say Hello">
</form>
</body>
</html>
