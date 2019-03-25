CREATE TABLE IF NOT EXISTS userss (
    id serial PRIMARY KEY,
    name VARCHAR(32),
    password VARCHAR(32)
    );

CREATE TABLE IF NOT EXISTS question(
    id serial PRIMARY KEY,
    title VARCHAR(32) NOT NULL,
    text VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS tagi(
    id serial PRIMARY KEY,
    name VARCHAR(32) NOT NULL

);
CREATE TABLE IF NOT EXISTS qtag(
    id serial PRIMARY KEY,
    id_q INT NOT NULL  REFERENCES question(id),

    id_t INT NOT NULL REFERENCES tagi(id),
      UNIQUE(id_q, id_t)
);