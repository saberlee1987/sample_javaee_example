<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>person detail</title>
</head>
<body>
<h3>person detail</h3>
<%--@elvariable id="person" type="com.saber.samplejavaee.model.query.Person"--%>
<c:if test="${person!=null}">
    <table border="2">
        <tr>
            <th>firstName</th>
            <td>${person.firstName}</td>
        </tr>
        <tr>
            <th>lastName</th>
            <td>${person.lastName}</td>
        </tr>
        <tr>
            <th>age</th>
            <td>${person.age}</td>
        </tr>
        <tr>
            <th>email</th>
            <td>${person.email}</td>
        </tr>
        <tr>
            <th>nationalCode</th>
            <td>${person.nationalCode}</td>
        </tr>
        <tr>
            <th>mobile</th>
            <td>${person.mobile}</td>
        </tr>
    </table>
</c:if>
<c:if test="${person==null}">
    <h3>your Person does not exist</h3>
</c:if>
<hr>
<a href="${pageContext.request.contextPath}/person">Back To Person List</a>
</body>
</html>
