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
Create Vote for Restaurant with id 100007 | curl -s -X POST -d '{"localDate":"2019-08-28","restaurant":{"id":100007,"name":"Ле Дюк"},"user":{"id":100003,"name":"Bart","email":"bart@bart.ru","enabled":true,"registered":"2019-08-28T19:29:06.147+0000","roles":["USER"]}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/caferate/rest/voices/ --user bart@bart.ru:bart
Get Vote | curl -s http://localhost:8080/caferate/rest/voices/100023 --user tom@tom.ru:tom
Get all Votes | curl -s http://localhost:8080/caferate/rest/voices/all --user tom@tom.ru:tom
Update Vote | curl -s -X PUT -d '{"id":100023, "localDate":"2019-08-28", "restaurant":{"id":100007,"name":"Ле Дюк"}, "user":{"id":100001,"name":"Tom","email":"tom@tom.ru","enabled":true,"registered":"2019-08-28T19:29:06.147+0000","roles":["USER"]}}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/voices/100023 --user tom@tom.ru:tom
Delete Vote | curl -s -X DELETE http://localhost:8080/caferate/rest/voices/100027 --user tom@tom.ru:tom


# Curl Rest Meals requests examples
Description | Command
----------- | -------
Add meal to Restaurant | curl -s -X POST -d '{"name":"new Dish", "day":"2018-08-30", "price":300}' -H 'Content-Type: application/json' http://localhost:8080/dish/add/100003 --user Admin:admin
Get All Meals | curl -s "http://localhost:8080/dish/getAll" --user Admin:admin
Get Dish
curl -s "http://localhost:8080/dish/100008" --user Admin:admin
Update Dish
curl -s -X PUT -d '{"id":100004, "name":"Updated Dish", "day":"2018-08-30", "price":300}' -H 'Content-Type: application/json' http://localhost:8080/dish/update/100002 --user Admin:admin
Delete Dish
curl -s -X DELETE http://localhost:8080/dish/100004 --user Admin:admin
Get Dishes between dates
curl -s "http://localhost:8080/dish/list?startDate=2015-05-31&endDate=2018-08-31" --user Admin:admin
Get Dishes with Restaurant id
curl -s "http://localhost:8080/dish/list/100003" --user Admin:admin

# Curl Rest Restaurants requests examples
Description | Command
----------- | -------
Create new Restaurant | curl -s -X POST -d '{"id": null, "name": "New Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/caferate/rest/restaurants/
Update Restaurant | curl -s -X PUT -d '{"id":100006, "name":"Updated Restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/restaurants/100006
Delete Restaurant | curl -s -X DELETE http://localhost:8080/caferate/rest/restaurants/100007
Get Restaurant | curl -s http://localhost:8080/caferate/rest/restaurants/100006 
Get All Restaurants | curl -s http://localhost:8080/caferate/rest/restaurants/all
Get All by Date | curl -s http://localhost:8080/caferate/rest/restaurants/all/date?localDate=2015-06-01

# Curl Rest Users requests examples
Description | Command
----------- | -------
Get all users
curl -s "http://localhost:8080/admin/getAll" --user Admin:admin
Get user
curl -s "http://localhost:8080/admin/100000" --user Admin:admin
Create new User
curl -s -X POST -d '{"login":"new User", "password":"12345", "roles":["ROLE_USER"]}' -H 'Content-Type: application/json' http://localhost:8080/admin/create --user Admin:admin
Update User
curl -s -X PUT -d '{"id":100000, "login":"updated User", "password":"12345", "roles":["ROLE_USER"]}' -H 'Content-Type: application/json' http://localhost:8080/admin/update --user Admin:admin
Delete User
curl -s -X DELETE http://localhost:8080/admin/100000 --user Admin:admin