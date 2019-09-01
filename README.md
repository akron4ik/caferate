# Restaurant Raiting
Task
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.


# Curl Rest Votes requests examples
Description | Command
----------- | -------
Create Vote for Restaurant with id 100021 | curl -s -X POST -d '{"date":"2019-09-01","restaurantId":100021,"userId":100003}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/caferate/rest/voices/ --user bart@bart.ru:bart
Get Vote | curl -s http://localhost:8080/caferate/rest/voices/100045 --user tom@tom.ru:tom
Get all Votes | curl -s http://localhost:8080/caferate/rest/voices/all --user tom@tom.ru:tom
Update Vote | curl -s -X PUT -d '{"id":100064, "date":"2019-08-20","restaurantId":100022, "userId":100001}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/voices/100064 --user tom@tom.ru:tom
Delete Vote | curl -s -X DELETE http://localhost:8080/caferate/rest/voices/100045 --user tom@tom.ru:tom

# Curl Rest Meals requests examples
Description | Command
----------- | -------
Create Meal | curl -s -X POST -d '{"id": null, "name": "New" ,"date":"2019-09-01", "price": 200.4, "restaurant":{"id":100022,"name":"Шинок"}}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/caferate/rest/meals/ --user admin@admin.ru:admin
Get All Meals by restaurant id | curl -s "http://localhost:8080/caferate/rest/meals/all/100020" --user admin@admin.ru:admin
Get Meal | curl -s "http://localhost:8080/caferate/rest/meals/100036" --user admin@admin.ru:admin
Update Meal | curl -s -X PUT -d '{"id":100030, "name":"Updated", "date":"2019-08-10", "price":666,"restaurant":{"id":100020,"name":"Капри"}}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/meals/100030 --user admin@admin.ru:admin
Delete Meal | curl -s -X DELETE http://localhost:8080/caferate/rest/meals/100038 --user admin@admin.ru:admin
Get All meals by Restaurant id and Date | curl -s http://localhost:8080/caferate/rest/meals/all/100020?localDate=2019-08-10 --user admin@admin.ru:admin
Get meal with restaurant | curl -s http://localhost:8080/caferate/rest/meals/restaurant/100031 --user admin@admin.ru:admin

# Curl Rest Restaurants requests examples
Description | Command
----------- | -------
Create new Restaurant | curl -s -X POST -d '{"id": null, "name": "New Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/caferate/rest/restaurants/ --user admin@admin.ru:admin
Update Restaurant | curl -s -X PUT -d '{"id":100027, "name":"Updated Restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/restaurants/100027 --user admin@admin.ru:admin
Delete Restaurant | curl -s -X DELETE http://localhost:8080/caferate/rest/restaurants/100026 --user admin@admin.ru:admin
Get Restaurant | curl -s http://localhost:8080/caferate/rest/restaurants/100027 --user admin@admin.ru:admin
Get All Restaurants | curl -s http://localhost:8080/caferate/rest/restaurants/all --user admin@admin.ru:admin
Get All Restaurants with meals by Date (MENU)| curl -s http://localhost:8080/caferate/rest/restaurants/all/date?localDate=2019-08-10 --user admin@admin.ru:admin
Get Rating by Restaurant and day | curl -s http://localhost:8080/caferate/rest/restaurants/rating/bydate/100020?localDate=2019-08-10 --user admin@admin.ru:admin
Get Rating by Restaurant between dates | curl -s "http://localhost:8080/caferate/rest/restaurants/rating/100020?startDate=2019-08-10&endDate=2019-08-20" --user admin@admin.ru:admin


# Curl Rest Users requests examples
Description | Command
----------- | -------
Get all users | curl -s "http://localhost:8080/caferate/rest/admin/users" --user admin@admin.ru:admin
Get user | curl -s "http://localhost:8080/caferate/rest/admin/users/100000" --user admin@admin.ru:admin
Create new User | curl -s -X POST -d '{"name":"New name","email":"newmail@gmail.com","enabled":true,"registered":"2019-09-01T14:52:15","roles":["ROlE_USER"],"password":"newPass"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/admin/users/ --user admin@admin.ru:admin
Update User | curl -s -X PUT -d '{"id":100001, "name":"Updated","email":"tom@tom.ru", "enabled":true,"registered":"2019-08-29T14:52:15","roles":["ROlE_USER"],"password":"tom"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/admin/users/100001 --user admin@admin.ru:admin
Delete User | curl -s -X DELETE http://localhost:8080/caferate/rest/admin/users/100002 --user admin@admin.ru:admin