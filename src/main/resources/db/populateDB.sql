DELETE FROM user_roles;
DELETE FROM cafes;
DELETE FROM users;
DELETE FROM meals;
DELETE FROM voices;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, appreciated) VALUES
  ('Admin', false ),
  ('Tom', false ),
  ('Homer', false ),
  ('Bart', false ),
  ('Liza', false ),
  ('Meggi', false );

INSERT INTO user_roles (role, user_id) VALUES
  ('ADMIN', 100000),
  ('USER', 100001),
  ('USER', 100002),
  ('USER', 100003),
  ('USER', 100004),
  ('USER', 100005);


INSERT INTO cafes (id, description, rating) VALUES
  (100006,'Капри', 100 ),
  (100007,'Ле Дюк', 200),
  (100008,'Шиннок', 300),
  (100009,'Шоколадница', 555),
  (100010,'МакДак', 400);

INSERT INTO meals (name, date_time, price, cafe_id) VALUES
  ('Цезарь', '2015-06-01 10:00:00', 350.50, 100006),
  ('Спагетти', '2015-06-01 10:00:00', 500.0, 100006),
  ('Шашлык', '2015-06-01 10:00:00', 440.8, 100006),
  ('Яичница', '2015-06-01 10:00:00', 30.50, 100007),
  ('Фетучини', '2015-06-01 10:00:00', 600.3, 100007),
  ('Греческий', '2015-06-01 10:00:00', 270.7, 100007),
  ('Оливье', '2015-06-01 10:00:00', 200.40, 100008),
  ('Селедка', '2015-06-01 10:00:00', 345.0, 100008),
  ('Цыпленок табака', '2015-06-01 10:00:00', 570.8, 100008),
  ('Омлет', '2015-06-01 10:00:00', 45.50, 100009),
  ('Мясо по-французки', '2015-06-01 10:00:00', 770.3, 100009),
  ('Каре ягненка', '2015-06-01 10:00:00', 885.5, 100009);

INSERT INTO voices (date_time, cafe_id, user_id) VALUES
  ('2015-06-01 10:00:00', 100006, 100001),
  ('2015-06-01 10:00:00', 100006, 100002),
  ('2015-06-01 10:00:00', 100007, 100003),
  ('2015-06-01 10:00:00', 100008, 100004),
  ('2015-06-01 10:00:00', 100008, 100005);




