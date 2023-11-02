CREATE TABLE book
    (
        id INTEGER  NOT NULL PRIMARY KEY,
        isbn VARCHAR(255) NOT NULL,
        name VARCHAR(255) NOT NULL,
        genre VARCHAR(255) NOT NULL,
        description VARCHAR(255) NOT NULL,
        author VARCHAR(255) NOT NULL
    );