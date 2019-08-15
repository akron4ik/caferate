DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM users;
DELETE FROM meals;
DELETE FROM voices;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password, registered) VALUES
  ('Admin', 'admin@admin.ru', 'admin', '2019-08-08 10:00:00.000000'),
  ('Tom', 'tom@tom.ru', 'tom', '2019-08-09 10:00:00.000000'),
  ('Homer', 'homer@homer.ru', 'homer', '2019-08-02 10:00:00.000000'),
  ('Bart', 'bart@bart.ru', 'bart', '2019-08-01 10:00:00.000000'),
  ('Liza', 'liza@liza.ru', 'liza', '2019-08-04 10:00:00.000000'),
  ('Meggi', 'meggi@meggi.ru', 'meggi', '2019-08-08 10:00:00.000000');

INSERT INTO user_roles (role, user_id) VALUES
  ('ADMIN', 100000),
  ('USER', 100001),
  ('USER', 100002),
  ('USER', 100003),
  ('USER', 100004),
  ('USER', 100005);


INSERT INTO restaurants (name) VALUES
  ('Капри' ),
  ('Ле Дюк'),
  ('Шиннок'),
  ('Шоколадница'),
  ('МакДак');

INSERT INTO meals (name, date_time, price, restaurant_id) VALUES
  ('Цезарь', '2015-06-01', 350.50, 100006),
  ('Ризотто', '2015-06-01', 500.5, 100006),
  ('Шашлык', '2015-06-01', 440.8, 100006),
  ('Яичница', '2015-06-01', 30.50, 100007),
  ('Фетучини', '2015-06-01', 600.3, 100007),
  ('Греческий', '2015-06-01', 270.7, 100007),
  ('Оливье', '2015-06-01', 200.40, 100008),
  ('Селедка', '2015-06-01', 345.0, 100008),
  ('Цыпленок табака', '2015-06-01', 570.8, 100008),
  ('Омлет', '2015-06-01', 45.50, 100009),
  ('Мясо по-французки', '2015-06-01', 770.3, 100009),
  ('Каре ягненка', '2015-06-01', 885.5, 100009);

INSERT INTO voices (date_time, restaurant_id, user_id) VALUES
  ('2015-06-01', 100006, 100001),
  ('2015-07-02', 100006, 100002),
  ('2015-08-03', 100007, 100003),
  ('2015-09-04', 100008, 100004),
  ('2015-10-05', 100008, 100005);




