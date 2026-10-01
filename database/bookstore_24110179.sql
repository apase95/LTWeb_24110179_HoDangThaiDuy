CREATE DATABASE IF NOT EXISTS bookstore_24110179
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE bookstore_24110179;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    fullname NVARCHAR(50) NULL,
    phone INT NULL,
    passwd VARCHAR(32) NOT NULL,
    signup_date DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    last_login DATETIME NULL,
    is_admin BIT NULL DEFAULT 0
);

CREATE TABLE books (
    bookid INT AUTO_INCREMENT PRIMARY KEY,
    isbn INT NULL,
    title VARCHAR(200) NULL,
    publisher VARCHAR(100) NULL,
    price DECIMAL(6,2) NULL,
    description TEXT NULL,
    publish_date DATE NULL,
    cover_image VARCHAR(100) NULL,
    quantity INT NULL
);

CREATE TABLE author (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(100) NULL,
    date_of_birth DATE NULL
);

CREATE TABLE book_author (
    bookid INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (bookid, author_id),
    FOREIGN KEY (bookid) REFERENCES books(bookid),
    FOREIGN KEY (author_id) REFERENCES author(author_id)
);

CREATE TABLE rating (
    userid INT NOT NULL,
    bookid INT NOT NULL,
    rating TINYINT NULL,
    review_text TEXT NULL,
    PRIMARY KEY (userid, bookid),
    FOREIGN KEY (userid) REFERENCES users(id),
    FOREIGN KEY (bookid) REFERENCES books(bookid)
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NULL,
    customer_name NVARCHAR(50) NOT NULL,
    customer_phone VARCHAR(20) NOT NULL,
    customer_address VARCHAR(255) NOT NULL,
    payment_method VARCHAR(20) NOT NULL DEFAULT 'COD',
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    bookid INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (bookid) REFERENCES books(bookid)
);

INSERT INTO users (email, fullname, phone, passwd, is_admin)
VALUES ('admin@bookstore.com', 'Admin', 123456789, '123456', 1),
       ('user@bookstore.com', 'User', 987654321, '123456', 0);

INSERT IGNORE INTO author (author_name, date_of_birth)
VALUES ('Author A', '1980-01-01'),
       ('Author B', '1990-05-15'),
       ('Author C', '1975-09-20'),
       ('Author D', '1988-12-12');

INSERT INTO books (isbn, title, publisher, price, description, publish_date, cover_image, quantity)
VALUES (2001, 'Java Web Book 01', 'NXB Tre', 99.00, 'Book demo 1', '2024-01-01', 'book-1.jpg', 10),
       (2002, 'Java Web Book 02', 'NXB Tre', 120.00, 'Book demo 2', '2024-01-02', 'book-2.jpg', 8),
       (2003, 'Java Web Book 03', 'NXB Tre', 150.00, 'Book demo 3', '2024-01-03', 'book-3.jpg', 6),
       (2004, 'JDBC Book 04', 'NXB Giao Duc', 110.00, 'Book demo 4', '2024-02-01', 'book-1.jpg', 12),
       (2005, 'JDBC Book 05', 'NXB Giao Duc', 130.00, 'Book demo 5', '2024-02-02', 'book-2.jpg', 9),
       (2006, 'JDBC Book 06', 'NXB Giao Duc', 140.00, 'Book demo 6', '2024-02-03', 'book-3.jpg', 7),
       (2007, 'Servlet Book 07', 'NXB Tong Hop', 115.00, 'Book demo 7', '2024-03-01', 'book-1.jpg', 15),
       (2008, 'Servlet Book 08', 'NXB Tong Hop', 125.00, 'Book demo 8', '2024-03-02', 'book-2.jpg', 11),
       (2009, 'Servlet Book 09', 'NXB Tong Hop', 135.00, 'Book demo 9', '2024-03-03', 'book-3.jpg', 5),
       (2010, 'MVC Book 10', 'NXB CNTT', 145.00, 'Book demo 10', '2024-04-01', 'book-1.jpg', 20),
       (2011, 'MVC Book 11', 'NXB CNTT', 155.00, 'Book demo 11', '2024-04-02', 'book-2.jpg', 14),
       (2012, 'MVC Book 12', 'NXB CNTT', 165.00, 'Book demo 12', '2024-04-03', 'book-3.jpg', 13);

INSERT INTO book_author (bookid, author_id)
SELECT b.bookid, (SELECT MIN(author_id) FROM author WHERE author_name = 'Author A') FROM books b WHERE b.isbn BETWEEN 2001 AND 2003;
INSERT INTO book_author (bookid, author_id)
SELECT b.bookid, (SELECT MIN(author_id) FROM author WHERE author_name = 'Author B') FROM books b WHERE b.isbn BETWEEN 2004 AND 2006;
INSERT INTO book_author (bookid, author_id)
SELECT b.bookid, (SELECT MIN(author_id) FROM author WHERE author_name = 'Author C') FROM books b WHERE b.isbn BETWEEN 2007 AND 2009;
INSERT INTO book_author (bookid, author_id)
SELECT b.bookid, (SELECT MIN(author_id) FROM author WHERE author_name = 'Author D') FROM books b WHERE b.isbn BETWEEN 2010 AND 2012;

INSERT INTO rating (userid, bookid, rating, review_text)
SELECT 2, bookid, 5, 'Sach hay' FROM books WHERE isbn IN (2001, 2005, 2009);
