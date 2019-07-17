DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS cafes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS voices;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  appreciated        BOOL DEFAULT TRUE       NOT NULL

);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE cafes
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  description TEXT      NOT NULL,
  rating   INT       NOT NULL
);

CREATE TABLE meals
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL,
  date_time TIMESTAMP NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  cafe_id INTEGER NOT NULL
  /*FOREIGN KEY (cafe_id) REFERENCES cafes(id)*/

);

CREATE TABLE voices
(
   id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
   date_time TIMESTAMP NOT NULL,
   cafe_id INTEGER NOT NULL,
   user_id INTEGER NOT NULL
  /* FOREIGN KEY (cafe_id) REFERENCES cafes(id)*/
   /*FOREIGN KEY (user_id) REFERENCES users(id)*/
);
/*CREATE UNIQUE INDEX uniq_datetime_to_userid ON voices(date_time, user_id);*/