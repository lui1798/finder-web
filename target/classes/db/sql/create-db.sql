DROP TABLE Book IF EXISTS;

CREATE TABLE Book (
  id    INTEGER PRIMARY KEY,
  author VARCHAR(100),
  isbn  VARCHAR(50),
  title VARCHAR(150)
);
