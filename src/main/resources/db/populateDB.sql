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
  ('Meggi', 'meggi@meggi.ru', '{noop}meggi');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROlE_ADMIN', 100000),
  ('ROlE_USER', 100001),
  ('ROlE_USER', 100002),
  ('ROlE_USER', 100003),
  ('ROlE_USER', 100004),
  ('ROlE_USER', 100005);


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
  ('Яичница', '2015-06-02', 30.50, 100006),
  ('Фетучини', '2015-06-02', 600.3, 100006),
  ('Греческий', '2015-06-02', 270.7, 100006),
  ('Оливье', '2015-06-01', 200.40, 100008),
  ('Селедка', '2015-06-03', 345.0, 100008),
  ('Цыпленок табака', '2015-06-01', 570.8, 100008),
  ('Омлет', '2015-06-01', 45.50, 100009),
  ('Мясо по-французки', '2015-06-01', 770.3, 100009),
  ('Каре ягненка', '2015-06-03', 885.5, 100009);

INSERT INTO voices (date_time, restaurant_id, user_id) VALUES
  ('2015-06-01', 100006, 100001),
  ('2015-09-04', 100008, 100002),
  ('2015-08-03', 100007, 100003),
  ('2015-09-04', 100008, 100001),
  ('2015-10-05', 100008,100001);




