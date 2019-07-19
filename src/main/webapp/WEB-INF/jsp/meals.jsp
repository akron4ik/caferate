<%--
  Created by IntelliJ IDEA.
  User: aron4ik
  Date: 2019-07-19
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<head>
    <title>All Meals</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>All Meals</h2>

    <hr/>
    <a href="restaurants?action=create">Add Cafe</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Время</th>
            <th>Id Ресторана</th>

        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <tr>

                <td>${meal.id}</td>
                <td>${meal.name}</td>
                <td>${meal.price}</td>
                <td>${meal.dateTime}</td>
                <td>${meal.restaurant.id}</td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
</section>
</body>
</html>

