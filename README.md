# Restaurant Rating
Task
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users

-Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)

-Menu changes each day (admins do the updates)

-Users can vote on which restaurant they want to have lunch at

Only one vote counted per user

If user votes again the same day:

If it is before 11:00 we asume that he changed his mind.

If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.


# Curl Rest Votes requests examples
Description | Command | Access
----------- | ------- | --------
Create Vote for Restaurant with id 100021 | curl -s -X POST -d '{"restaurantId":100021}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/caferate/rest/voices/ --user bart@bart.ru:bart | Authorized
Get Vote | curl -s http://localhost:8080/caferate/rest/voices/100045 --user tom@tom.ru:tom | Authorized
Get all Votes | curl -s http://localhost:8080/caferate/rest/voices/ --user tom@tom.ru:tom | Authorized
Update Vote | curl -s -X PUT -d '{"id":100064, "date":"2019-08-20","restaurantId":100022}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/voices/100064 --user tom@tom.ru:tom | Authorized
Delete Vote | curl -s -X DELETE http://localhost:8080/caferate/rest/voices/100045 --user tom@tom.ru:tom | Authorized

# Curl Rest Meals requests examples
Description | Command | Access
----------- | ------- | --------
Create Meal | curl -s -X POST -d '{"id": null, "name": "New" ,"date":"2019-09-01", "price": 200.4, "restaurantId": 100022}' -H 'Content-Type: application/json;charset=UTF-8' "http://localhost:8080/caferate/rest/meals/" --user admin@admin.ru:admin | Admin
Get All Meals by restaurant id | curl -s "http://localhost:8080/caferate/rest/meals/?restaurantId=100020" --user bart@bart.ru:bart | Authorized
Get Meal | curl -s http://localhost:8080/caferate/rest/meals/100036 --user bart@bart.ru:bart | Authorized 
Update Meal | curl -s -X PUT -d '{"id":100030, "name":"Updated", "date":"2019-08-10", "price":666,"restaurantId":100020}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/meals/100030 --user admin@admin.ru:admin | Admin
Delete Meal | curl -s -X DELETE http://localhost:8080/caferate/rest/meals/100038 --user admin@admin.ru:admin | Admin
Get All meals by Restaurant id and Date | curl -s 'http://localhost:8080/caferate/rest/meals/?restaurantId=100020&localDate=2019-08-10' --user bart@bart.ru:bart | Authorized


# Curl Rest Restaurants requests examples
Description | Command | Access
----------- | ------- | --------
Create new Restaurant | curl -s -X POST -d '{"id": null, "name": "New Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/caferate/rest/restaurants/ --user admin@admin.ru:admin | Admin
Update Restaurant | curl -s -X PUT -d '{"id":100027, "name":"Updated Restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/restaurants/100027 --user admin@admin.ru:admin | Admin
Delete Restaurant | curl -s -X DELETE http://localhost:8080/caferate/rest/restaurants/100026 --user admin@admin.ru:admin | Admin
Get Restaurant without meals | curl -s http://localhost:8080/caferate/rest/restaurants/100024 --user bart@bart.ru:bart  | Authorized
Get Restaurant with meals | curl -s http://localhost:8080/caferate/rest/restaurants/meal/100024 --user bart@bart.ru:bart | Authorized
Get All Restaurants without Meals | curl -s http://localhost:8080/caferate/rest/restaurants/all/?localDate=2019-08-10 --user bart@bart.ru:bart | Authorized
Get All Restaurants with meals by Date (MENU)| curl -s http://localhost:8080/caferate/rest/restaurants/?localDate=2019-08-10 --user bart@bart.ru:bart | Authorized


# Curl Rest Admin requests examples
Description | Command | Access
----------- | ------- | -------
Get all users | curl -s "http://localhost:8080/caferate/rest/admin/users" --user admin@admin.ru:admin | Admin
Get user | curl -s "http://localhost:8080/caferate/rest/admin/users/100000" --user admin@admin.ru:admin | Admin
Create new User | curl -s -X POST -d '{"name":"New name","email":"newmail@gmail.com","enabled":true,"registered":"2019-09-01T14:52:15","roles":["ROlE_USER"],"password":"newPass"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/admin/users/ --user admin@admin.ru:admin | Admin
Update User | curl -s -X PUT -d '{"id":100001, "name":"Updated","email":"tom@tom.ru", "enabled":true,"registered":"2019-08-29T14:52:15","roles":["ROlE_USER"],"password":"tom"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/admin/users/100001 --user admin@admin.ru:admin | Admin
Delete User | curl -s -X DELETE http://localhost:8080/caferate/rest/admin/users/100002 --user admin@admin.ru:admin | Admin
Get User by Email | curl -s "http://localhost:8080/caferate/rest/admin/users/by?email=tom@tom.ru" --user admin@admin.ru:admin | Admin

# Curl Rest Profile requests examples
Description | Command | Access
----------- | ------- | -------
Get User | curl -s "http://localhost:8080/caferate/rest/profile" --user bart@bart.ru:bart | Authorized
Update User | curl -s -X PUT -d '{"name": "Update","email": "bart@bart.ru", "password":"bart"}' -H 'Content-Type: application/json' http://localhost:8080/caferate/rest/profile --user bart@bart.ru:bart | Authorized
Delete User | curl -s -X DELETE "http://localhost:8080/caferate/rest/profile" -user tom@tom.ru:tom | Authorized
Register User | curl -s -X POST -d '{"name": "NewUser","email": "new@new.ru","password": "password"}' -H 'Content-Type: application/json;charset=UTF-8' http://localhost:8080/caferate/rest/profile/register | Anonymous
