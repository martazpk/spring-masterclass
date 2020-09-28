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
    <h1>Add product</h1>
    <sf:form modelAttribute="productTransferObject" method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <sf:input path="name" class="form-control"/>
            <sf:errors path="name"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <sf:input path="description" class="form-control"/>
            <sf:errors path="description"/>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <sf:input path="price" class="form-control"/>
            <sf:errors path="price"/>
        </div>
        <div>
            <div class="form-group">
                <label for="type">Type</label>
                <sf:select path="type" class="form-control">
                    <sf:option value="EBOOK">Ebook</sf:option>
                    <sf:option value="AUDIO">Audio</sf:option>
                    <sf:option value="VIDEO">Video</sf:option>
                </sf:select>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </sf:form>
</div>
</body>
</html>
