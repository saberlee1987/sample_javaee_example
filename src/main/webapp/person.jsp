<%--@elvariable id="person" type="com.saber.samplejavaee.model.query.Person"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>person page</title>
</head>
<body>
<h3>person page</h3>
<%--@elvariable id="violations" type="java.util.Set<javax.validation.ConstraintViolation<com.saber.samplejavaee.model.query.Person>>"--%>
<c:if test="${violations!=null && violations.size()>0}">
    <ul>
        <c:forEach items="${violations}" var="v">
            <li>${v.message}</li>
        </c:forEach>
    </ul>
</c:if>
<form action="${pageContext.request.contextPath}/person" method="post">
    <input type="hidden" name="action" value="save">

    <label for="firstName">firstName</label>
    <input type="text" id="firstName" name="firstName" size="15" value="${person.firstName}" placeholder="firstName"> <br><br>

    <label for="lastName">lastName</label>
    <input type="text" id="lastName" name="lastName" size="15" value="${person.lastName}" placeholder="lastName"> <br><br>

    <label for="age">age</label>
    <input type="text" id="age" name="age" size="15" value="${person.age}" placeholder="age"> <br><br>

    <label for="email">email</label>
    <input type="email" id="email" name="email" size="15" value="${person.email}" placeholder="email"> <br><br>

    <label for="nationalCode">nationalCode</label>
    <input type="text" id="nationalCode" name="nationalCode" size="15" value="${person.nationalCode}" placeholder="nationalCode"> <br><br>

    <label for="mobile">mobile</label>
    <input type="text" id="mobile" name="mobile" size="15" value="${person.mobile}" placeholder="mobile"> <br><br>
    <input type="submit" value="Add Person">
</form>
<%--@elvariable id="persons" type="java.util.List<com.saber.samplejavaee.model.query.Person>"--%>
<c:if test="${persons!=null && persons.size()>0}">
    <table border="2">
        <tr>
            <th>firstName</th>
            <th>lastName</th>
            <th>age</th>
            <th>email</th>
            <th>nationalCode</th>
            <th>mobile</th>
            <th>Detail</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${persons}" var="person">
            <tr>
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
                <td>${person.age}</td>
                <td>${person.email}</td>
                <td>${person.nationalCode}</td>
                <td>${person.mobile}</td>
                <td><a href="${pageContext.request.contextPath}/person/${person.id}?action=detail">Detail</a></td>
                <td><a href="${pageContext.request.contextPath}/person/${person.id}?action=delete">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
