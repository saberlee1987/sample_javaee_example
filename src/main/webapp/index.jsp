<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h3>Hello World @@@</h3>
<h3><a href="${pageContext.request.contextPath}/hello.jsp">Hello Page</a></h3>
<h3><a href="${pageContext.request.contextPath}/person?action=list">person Page</a></h3>
<hr>
<table border="2">
    <c:forEach begin="1" end="10" var="i">
        <tr>
            <c:forEach begin="1" end="10" var="j">
                <td>${i*j}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
