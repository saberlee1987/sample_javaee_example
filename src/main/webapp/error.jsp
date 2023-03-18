<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<%--@elvariable id="errorMessage" type="java.lang.String"--%>
<%--@elvariable id="statusCode" type="java.lang.String"--%>
<%--@elvariable id="servletName" type="java.lang.String"--%>
<%--@elvariable id="requestUri" type="java.lang.String"--%>

<h3>error is occurred, please call admin system </h3>
<ul>
    <li> errorMessage ===> ${errorMessage}</li>
    <li> statusCode ===> ${statusCode}</li>
    <li> servletName ===> ${servletName}</li>
    <li> requestUri ===> ${requestUri}</li>
</ul>
<hr>
<a href="${pageContext.request.contextPath}/person">Back To Person List</a>
</body>
</html>
