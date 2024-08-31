CREATE TABLE catsfriends (cat_id INTEGER, friend_id INTEGER);
CREATE TABLE cats (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255),
                      datebirth DATE,
                      breed VARCHAR(255),
                      owner_id INTEGER,
                      FOREIGN KEY (owner_id) REFERENCES public.owners(id)
);
CREATE TABLE owners (id SERIAL PRIMARY KEY,
                     name VARCHAR(255),
                     datebirth DATE)
;
CREATE TABLE users (id SERIAL PRIMARY KEY,
                    name VARCHAR(255),
                    password VARCHAR(255),
                    owner_id INTEGER,
                    FOREIGN KEY (owner_id) REFERENCES public.owners(id)
                    )
;