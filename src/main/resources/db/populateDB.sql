DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM meals;
DELETE FROM voices;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@admin.ru', '{noop}admin' ),
  ('Tom', 'tom@tom.ru', '{noop}tom'),
  ('Homer', 'homer@homer.ru', '{noop}homer'),
  ('Bart', 'bart@bart.ru', '{noop}bart'),
  ('Liza', 'liza@liza.ru', '{noop}liza'),
  ('Meggi', 'meggi@meggi.ru', '{noop}meggi'),
  ('Marge', 'marge@marge.ru','{noop}marge'),
  ('Felix', 'felix@felix.ru','{noop}felix'),
  ('Peter', 'peter@peter.ru','{noop}peter'),
  ('Gregori', 'gregori@gregori.ru','{noop}gregori'),
  ('Harry', 'harry@harry.ru','{noop}harry'),
  ('Germiona', 'germiona@germiona.ru','{noop}germiona'),
  ('Ron', 'ron@ron.ru','{noop}ron'),
  ('Malfoi', 'malfoi@malfoi.ru','{noop}malfoi'),
  ('Dambldor', 'dambldor@dambldor.ru','{noop}dambldor'),
  ('Volandemort', 'volandemort@volandemort.ru','{noop}volandemort'),
  ('Nevil', 'nevil@nevil.ru','{noop}nevil'),
  ('Jameson', 'jameson@jameson.ru','{noop}jameson'),
  ('Patric', 'patric@patric.ru','{noop}patric'),
  ('Batman', 'batman@batman.ru','{noop}batman');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROlE_ADMIN', 100000),
  ('ROlE_USER', 100001),
  ('ROlE_USER', 100002),
  ('ROlE_USER', 100003),
  ('ROlE_USER', 100004),
  ('ROlE_USER', 100005),
  ('ROlE_USER', 100006),
  ('ROlE_USER', 100007),
  ('ROlE_USER', 100008),
  ('ROlE_USER', 100009),
  ('ROlE_USER', 100010),
  ('ROlE_USER', 100011),
  ('ROlE_USER', 100012),
  ('ROlE_USER', 100013),
  ('ROlE_USER', 100014),
  ('ROlE_USER', 100015),
  ('ROlE_USER', 100016),
  ('ROlE_USER', 100017),
  ('ROlE_USER', 100018),
  ('ROlE_USER', 100019);

INSERT INTO restaurants (name) VALUES
  ('Капри'),/*20*/
  ('Маркет'),/*21*/
  ('Шинок'),/*22*/
  ('Ля маре'),/*23*/
  ('Боно'),/*24*/
  ('Ермак'),/*25*/
  ('Турандот'),/*26*/
  ('Пушкин'),/*27*/
  ('Уголек'),/*28*/
  ('Обломов');/*29*/


INSERT INTO meals (name, date_time, price, restaurant_id) VALUES
  ('Цезарь', '2019-08-10', 350.50, 100020),
  ('Ризотто', '2019-08-10', 500.5, 100020),
  ('Шашлык', '2019-08-10', 440.8, 100020),
  ('Яичница', '2019-08-10', 30.50, 100021),
  ('Фетучини', '2019-08-10', 600.3, 100021),
  ('Греческий', '2019-08-10', 270.7, 100021),
  ('Оливье', '2019-08-10', 200.40, 100022),
  ('Селедка', '2019-08-10', 345.0, 100022),
  ('Цыпленок табака', '2019-08-10', 570.8, 100022),
  ('Омлет', '2019-08-10', 45.50, 100023),
  ('Мясо по-французки', '2019-08-10', 770.3, 100023),
  ('Каре ягненка', '2019-08-10', 885.5, 100023),
  ('Борщ', '2019-08-10', 432.5, 100024),
  ('Паэлья','2019-08-10', 78.2, 100024),
  ('Пицца','2019-08-10', 150, 100024);

INSERT INTO voices (date_time, restaurant_id, user_id) VALUES
  ('2019-08-10', 100020, 100001),
  ('2019-08-10', 100020, 100002),
  ('2019-08-10', 100020, 100003),
  ('2019-08-10', 100020, 100004),
  ('2019-08-10', 100020, 100005),
  ('2019-08-10', 100020, 100006),
  ('2019-08-10', 100020, 100007),
  ('2019-08-10', 100020, 100008),
  ('2019-08-10', 100020, 100009),
  ('2019-08-10', 100020, 100010),
  ('2019-08-10', 100021, 100011),
  ('2019-08-10', 100021, 100012),
  ('2019-08-10', 100021, 100013),
  ('2019-08-10', 100022, 100014),
  ('2019-08-10', 100022, 100015),
  ('2019-08-10', 100023, 100016),
  ('2019-08-10', 100023, 100017),
  ('2019-08-10', 100024, 100018),
  ('2019-08-10', 100025, 100019),
  ('2019-08-20', 100020, 100001),
  ('2019-08-20', 100020, 100002),
  ('2019-08-20', 100020, 100003),
  ('2019-08-20', 100020, 100004),
  ('2019-08-20', 100021, 100005),
  ('2019-08-20', 100021, 100006),
  ('2019-08-20', 100021, 100007),
  ('2019-08-20', 100022, 100008),
  ('2019-08-20', 100022, 100009),
  ('2019-08-20', 100022, 100010);







