DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS meals CASCADE;
DROP TABLE IF EXISTS voices CASCADE;
DROP SEQUENCE IF EXISTS glob_seq CASCADE ;

CREATE SEQUENCE glob_seq START WITH 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('glob_seq'),
  name             VARCHAR                 NOT NULL

);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  /*CONSTRAINT user_roles_idx UNIQUE (user_id, role),*/
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('glob_seq'),
  description TEXT      NOT NULL,
  rating   INT       NOT NULL
);

CREATE TABLE meals
(
  id INTEGER PRIMARY KEY DEFAULT nextval('glob_seq'),
  name VARCHAR NOT NULL,
  date_time TIMESTAMP NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE

);

CREATE TABLE voices
(
   id INTEGER PRIMARY KEY DEFAULT nextval('glob_seq'),
   date_time TIMESTAMP NOT NULL,
   restaurant_id INTEGER NOT NULL,
   user_id INTEGER NOT NULL,
   FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
   FOREIGN KEY (user_id) REFERENCES users(id)
);
/*CREATE UNIQUE INDEX uniq_datetime_to_userid ON voices(date_time, user_id);*/