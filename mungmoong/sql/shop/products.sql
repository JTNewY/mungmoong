DROP TABLE IF EXISTS products;
CREATE TABLE `PRODUCTS` (
	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
	`NAME`	VARCHAR(100)	NOT NULL,
	`CATEGORY`	VARCHAR(100)	NULL,
	`DESCRIPTION`	VARCHAR(200)	NULL,
	`CONTENT`	TEXT	NULL,
	`PRICE`	INT	NOT NULL,
	`STOCK`	INT	NULL	DEFAULT 0,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);


TRUNCATE PRODUCTS;

-- Inserting sample data
INSERT INTO `PRODUCTS` (`ID`, `NAME`, `CATEGORY`, `DESCRIPTION`, `CONTENT`, `PRICE`, `STOCK`, `CREATED_AT`, `UPDATED_AT`) 
VALUES
('5e6f7g8h-9012-34ab-cdef-5678901234ef', '상의 상품 1', 'TOP', '상의 상품 1 설명', '상의 상품 1의 내용', 10000, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('6f7g8h9i-0123-45ab-cdef-6789012345fg', '상의 상품 2', 'TOP', '상의 상품 2 설명', '상의 상품 2의 내용', 15000, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('7g8h9i0j-1234-56ab-cdef-7890123456gh', '상의 상품 3', 'TOP', '상의 상품 3 설명', '상의 상품 3의 내용', 20000, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('8h9i0j1k-2345-67ab-cdef-8901234567hi', '하의 상품 1', 'BOTTOM', '하의 상품 1 설명', '하의 상품 1의 내용', 30000, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('9i0j1k2l-3456-78ab-cdef-9012345678ij', '하의 상품 2', 'BOTTOM', '하의 상품 2 설명', '하의 상품 2의 내용', 35000, 25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('0j1k2l3m-4567-89ab-cdef-0123456789jk', '하의 상품 3', 'BOTTOM', '하의 상품 3 설명', '하의 상품 3의 내용', 40000, 35, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('1k2l3m4n-5678-90ab-cdef-1234567890lm', '아우터 상품 1', 'OUTER', '아우터 상품 1 설명', '아우터 상품 1의 내용', 50000, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('2l3m4n5o-6789-01ab-cdef-2345678901mn', '아우터 상품 2', 'OUTER', '아우터 상품 2 설명', '아우터 상품 2의 내용', 55000, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('3m4n5o6p-7890-12ab-cdef-3456789012no', '아우터 상품 3', 'OUTER', '아우터 상품 3 설명', '아우터 상품 3의 내용', 60000, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('4n5o6p7q-8901-23ab-cdef-4567890123op', '악세사리 상품 1', 'ACCESSORY', '악세사리 상품 1 설명', '악세사리 상품 1의 내용', 7000, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('5o6p7q8r-9012-34ab-cdef-5678901234pq', '악세사리 상품 2', 'ACCESSORY', '악세사리 상품 2 설명', '악세사리 상품 2의 내용', 7500, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('6p7q8r9s-0123-45ab-cdef-6789012345qr', '악세사리 상품 3', 'ACCESSORY', '악세사리 상품 3 설명', '악세사리 상품 3의 내용', 8000, 70, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


SELECT * FROM products;
SELECT id FROM products;


SELECT p.id
              ,p.name
              ,p.category
              ,( SELECT name FROM categories WHERE code = p.category ) category_name
              ,p.content
              ,p.price
              ,p.stock
              ,p.created_at
              ,p.updated_at
        FROM products p


		;

		SELECT * FROM categories;


SELECT p.id
              ,p.name
              ,p.category
              ,p.description
              ,p.content
              ,p.price
              ,p.stock
              ,p.created_at
              ,p.updated_at
              ,( SELECT id FROM files WHERE parent_table = 'products' AND parent_id = p.id AND is_main = 1 ) thumbnail_id 
              ,( SELECT name FROM categories WHERE code = p.category ) category_name
        FROM products p
		;