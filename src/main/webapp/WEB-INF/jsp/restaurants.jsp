<%--
  Created by IntelliJ IDEA.
  User: aron4ik
  Date: 2019-07-19
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<head>
    <title>Restaurant with Meals</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>History Restaurant Food</h2>

    <hr/>
    <a href="restaurants?action=create">Add Cafe</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Rating</th>
            <th>Menu</th>

        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
            <tr>

            <td>${restaurant.id}</td>
            <td>${restaurant.description}</td>
            <td>${restaurant.rating}</td>
            <td><c:forEach items="${restaurant.meals}" var="meal">
                <td>${meal.name}</td>
                <td>${meal.price}</td>
                <td>${meal.dateTime}</td>
                </c:forEach>
            </td>

            <%--<td><a href="restaurants?action=update&id=${restaurant.id}">Update</a></td>
            <td><a href="restaurants?action=delete&id=${restaurant.id}">Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
    <br><br>
</section>
</body>
</html>
