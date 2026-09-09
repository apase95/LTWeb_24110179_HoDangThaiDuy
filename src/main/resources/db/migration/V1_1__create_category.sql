CREATE TABLE IF NOT EXISTS category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name NVARCHAR(255),
    images NVARCHAR(500),
    status INT
);