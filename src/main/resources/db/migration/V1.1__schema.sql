DROP TABLE IF EXISTS catsfriends CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS cats CASCADE;
DROP TABLE IF EXISTS owners CASCADE;

CREATE TABLE owners (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        datebirth DATE
);

CREATE TABLE cats (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255),
                      datebirth DATE,
                      breed VARCHAR(255),
                      owner_id INTEGER,
                      FOREIGN KEY (owner_id) REFERENCES public.owners(id)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       password VARCHAR(255),
                       owner_id INTEGER,  -- Добавляем owner_id в таблицу users
                       FOREIGN KEY (owner_id) REFERENCES public.owners(id)
);

CREATE TABLE catsfriends (
                             cat_id INTEGER,
                             friend_id INTEGER
);