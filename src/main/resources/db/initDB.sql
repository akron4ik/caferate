DROP TABLE voices IF EXISTS;
DROP TABLE meals IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;



DROP SEQUENCE GLOBAL_SEQ IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(225)                 NOT NULL,
  email            VARCHAR(225)                 NOT NULL,
  password         VARCHAR(225)                 NOT NULL,
  registered       TIMESTAMP DEFAULT now()      NOT NULL,
  enabled          BOOLEAN DEFAULT TRUE         NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER       NOT NULL,
  role    VARCHAR(225)  NOT NULL ,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name VARCHAR(225)     NOT NULL

);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE meals
(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name            VARCHAR(225)        NOT NULL,
  date_time       DATE DEFAULT now()  NOT NULL,
  price           DOUBLE              NOT NULL,
  restaurant_id   INTEGER             NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_name_idx ON meals (restaurant_id, name, date_time);

CREATE TABLE voices
(
   id INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
   date_time     DATE DEFAULT now() NOT NULL,
   restaurant_id INTEGER            NOT NULL,
   user_id       INTEGER            NOT NULL,
   FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE ,
   FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX voices_unique_datetime_idx ON voices (date_time, user_id);