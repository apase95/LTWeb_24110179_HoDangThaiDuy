CREATE TABLE IF NOT EXISTS product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name NVARCHAR(255) NOT NULL,
    price DOUBLE,
    quantity INT,
    description TEXT,
    images NVARCHAR(500),
    status INT,
    created_date DATETIME,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE SET NULL
);