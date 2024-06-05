DROP TABLE IF EXISTS ORDERS;
CREATE TABLE Orders (
    id UUID PRIMARY KEY,
    user_id VARCHAR(255),
    title VARCHAR(255),
    total_quantity INT NOT NULL DEFAULT 1,
    total_count INT NOT NULL DEFAULT 1,
    total_price INT NOT NULL DEFAULT 0,
    date_day TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'paid', 'shipping', 'delivered', 'cancelled') NOT NULL DEFAULT 'pending' COMMENT '결제대기, 결제완료, 배송중,배송완료,주문취소',
    ordered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

------------------------------------------------------------------------------
INSERT INTO `ORDERS` (`USER_ID`, `TITLE`, `TOTAL_QUANTITY`, `TOTAL_COUNT`, `TOTAL_PRICE`, `date_day`, `STATUS`, `ORDERED_AT`, `CREATED_AT`, `UPDATED_AT`)
VALUES 
('t', 'First Order', 2, 2, 1000, '2024-06-01 12:00:00', 'paid', '2024-06-01 12:00:00', '2024-06-01 12:00:00', '2024-06-01 12:00:00'),
('user', 'Second Order', 1, 1, 500, '2024-06-02 13:00:00', 'pending', '2024-06-02 13:00:00', '2024-06-02 13:00:00', '2024-06-02 13:00:00'),
('user_3', 'Third Order', 5, 5, 2500, '2024-06-03 14:00:00', 'shipping', '2024-06-03 14:00:00', '2024-06-03 14:00:00', '2024-06-03 14:00:00'),
('user_4', 'Fourth Order', 3, 3, 1500, '2024-06-04 15:00:00', 'delivered', '2024-06-04 15:00:00', '2024-06-04 15:00:00', '2024-06-04 15:00:00'),
('user_5', 'Fifth Order', 4, 4, 2000, '2024-06-05 16:00:00', 'cancelled', '2024-06-05 16:00:00', '2024-06-05 16:00:00', '2024-06-05 16:00:00');

TRUNCATE orders;
INSERT INTO orders (id, user_id, title, total_quantity, total_price, status, ordered_at, created_at, updated_at) 
VALUES(UUID(), #{user_id}, #{title}, #{total_quantity}, #{total_price}, #{status}, #{ordered_at}, #{created_at}, #{updated_at});

