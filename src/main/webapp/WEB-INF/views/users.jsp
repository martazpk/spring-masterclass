<%--
  Created by IntelliJ IDEA.
  User: koppm
  Date: 14.09.2020
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shop</title>
    <%@ include file="resources.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
    <c:if test="${users.totalPages > 0}">
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users.data}">
            <tr>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.emailAddress}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <c:if test="${users.pageNumber > 0}">
            <a href="show-users.html?pageNumber=${users.pageNumber -1}">Back</a>
        </c:if>
        <c:if test="${users.pageNumber + 1 < users.totalPages}">
            <a href="show-users.html?pageNumber=${users.pageNumber +1}" class="float-right">Next</a>
        </c:if>
        <div class="text-center">${users.pageNumber + 1} / ${users.totalPages}</div>
    </c:if>
</div>
</body>
</html>
