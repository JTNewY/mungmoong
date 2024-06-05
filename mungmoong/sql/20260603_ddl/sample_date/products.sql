DROP TABLE IF EXISTS products;
CREATE TABLE `PRODUCTS` (
	`ID`    CHAR(50)     NOT NULL,
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
INSERT INTO `PRODUCTS` (ID, NAME, CATEGORY, DESCRIPTION, CONTENT, PRICE, STOCK, CREATED_AT, UPDATED_AT)
VALUES 
('1', 'Dog Training Toy', '1', 'Interactive dog training toy', 'This toy helps in training and mental stimulation.', 15000, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('2', 'Cat Climbing Tree', '2', 'Multi-level cat climbing tree', 'Provides a fun and safe environment for cats to play and rest.', 30000, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('3', 'Pet Water Fountain', '3', 'Automatic pet water fountain', 'Ensures your pet always has access to fresh and clean water.', 25000, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('4', 'Bird Cage', 'Cages', '4', 'A large cage suitable for various bird species, including toys.', 40000, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

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