CREATE TABLE IF NOT EXISTS Product (
    ProductId INT AUTO_INCREMENT PRIMARY KEY,
    ProductName NVARCHAR(255) NOT NULL,
    Price DECIMAL(10,2),
    Quantity INT,
    Description TEXT,
    Images NVARCHAR(500),
    Status INT,
    CreatedDate DATE,
    CategoryId INT,
    FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId) ON DELETE SET NULL
);

INSERT INTO Product (ProductName, Price, Quantity, Description, Images, Status, CreatedDate, CategoryId)
SELECT 'Sản phẩm 1', 100000, 10, 'Mô tả sản phẩm 1', 'default.png', 1, CURDATE(), CategoryId
FROM Category WHERE CategoryName = 'Nerd' LIMIT 1;