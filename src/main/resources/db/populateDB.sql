DELETE FROM user_roles;
DELETE FROM cafes;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, appreciated) VALUES
  ('User', false ),
  ('Admin', false );

INSERT INTO user_roles (role, user_id) VALUES
  ('USER', 100000),
  ('ADMIN', 100001);

INSERT INTO cafes (description, rating, user_id)
VALUES ('Капри', 100, 100000),
       ('Ле Дюк', 200, 100000),
       ('Шиннок', 300, 100000),
       ('Шоколадница', 555, 100001);

