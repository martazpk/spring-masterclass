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
    <h1>Add user</h1>
    <sf:form modelAttribute="user" method="post">
        <div class="form-group">
            <label for="name">First name</label>
            <sf:input path="name" class="form-control"/>
            <sf:errors path="name"/>
        </div>
        <div class="form-group">
            <label for="surname">Surname</label>
            <sf:input path="surname" class="form-control"/>
            <sf:errors path="surname"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <sf:input path="email" class="form-control"/>
            <sf:errors path="email"/>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </sf:form>
</div>
</body>
</html>
