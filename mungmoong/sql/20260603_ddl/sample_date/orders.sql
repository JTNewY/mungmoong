DROP TABLE IF EXISTS ORDERS;
CREATE TABLE `ORDERS` (
	`ID`	CHAR(36)	NOT NULL,
	`USER_ID`	CHAR(36)	NOT NULL,
	`TITLE`	VARCHAR(255)	NULL,
	`TOTAL_QUANTITY`	INT	NOT NULL	DEFAULT 1,
	`TOTAL_COUNT`	INT	NOT NULL	DEFAULT 1,
	`TOTAL_PRICE`	INT	NOT NULL	DEFAULT 0,
	`STATUS`	ENUM('pending','paid','shipping','delivered','cancelled')	NOT NULL	DEFAULT 'pending'	COMMENT '결제대기, 결제완료, 배송중,배송완료,주문취소',
	`ORDERED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);
------------------------------------------------------------------------------


TRUNCATE orders;
INSERT INTO orders (id, user_id, title, total_quantity, total_price, status, ordered_at, created_at, updated_at) 
VALUES(UUID(), #{user_id}, #{title}, #{total_quantity}, #{total_price}, #{status}, #{ordered_at}, #{created_at}, #{updated_at});

