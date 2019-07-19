<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>All Users</h2>

    <hr/>
    <a href="restaurants?action=create">Add Cafe</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Роль</th>

        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>

                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.roles}</td>

            </tr>
        </c:forEach>
    </table>
    <br><br>
</section>
</body>
</html>