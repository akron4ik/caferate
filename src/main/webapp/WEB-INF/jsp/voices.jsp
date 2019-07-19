<%--
  Created by IntelliJ IDEA.
  User: aron4ik
  Date: 2019-07-19
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<head>
    <title>All Voices</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>All Voices</h2>

    <hr/>
    <a href="restaurants?action=create">Add Cafe</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Дата</th>
            <th>Кто проголосовал</th>
            <th>За кого проголосовали</th>

        </tr>
        </thead>
        <c:forEach items="${voices}" var="voice">
            <tr>

                <td>${voice.id}</td>
                <td>${voice.localDateTime}</td>
                <td>${voice.user.id}</td>
                <td>${voice.restaurant.id}</td>

            </tr>
        </c:forEach>
    </table>
    <br><br>
</section>
</body>
</html>
