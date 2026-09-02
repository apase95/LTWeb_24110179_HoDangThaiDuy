CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    username VARCHAR(255) NOT NULL UNIQUE,
    fullname VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    avatar VARCHAR(255),
    roleid INT,
    phone VARCHAR(20),
    createddate DATE
);

INSERT INTO User (email, username, fullname, password, roleid, phone, createddate) 
VALUES ('thaiduy@email.com', 'thaiduy', 'Thái Duy', '123456', 3, '0123456789', CURDATE())
ON DUPLICATE KEY UPDATE email = VALUES(email);