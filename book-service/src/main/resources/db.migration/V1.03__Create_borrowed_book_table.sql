CREATE TABLE borrowed_book
    (
        id INTEGER NOT NULL PRIMARY KEY,
        book_id INTEGER NOT NULL,
        borrowed_date DATE NOT NULL DEFAULT CURRENT_DATE,
        return_date DATE NOT NULL
    );