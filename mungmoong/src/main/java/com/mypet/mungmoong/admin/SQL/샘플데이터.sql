-- Active: 1713353106333@@127.0.0.1@3306@mypet
-- 샘플 데이터 삽입
-- pet 테이블
INSERT INTO `pet` (`no`, `file_no`, `name`, `age`, `petgender`, `character`, `type`)
VALUES
(1, 101, 'Buddy', 3, 1, 'Friendly', 'Dog'),
(2, 102, 'Mittens', 2, 2, 'Curious', 'Cat'),
(3, 103, 'Charlie', 4, 1, 'Playful', 'Dog'),
(4, 104, 'Bella', 1, 2, 'Shy', 'Cat'),
(5, 105, 'Max', 5, 1, 'Energetic', 'Dog');

-- reserve 테이블
INSERT INTO `reserve` (`no`, `user_id`, `trainer_no`, `request`, `condition`, `TITLE`, `TOTAL_QUANTITIY`, `TOTAL_COUNT`, `TOTAL_PRICE`, `STATUS`)
VALUES
(1, 'user1', 201, 'Special diet', 'Good', 'Training', 1, 1, 100, 'PENDING'),
(2, 'user2', 202, 'Daily walk', 'Average', 'Exercise', 1, 1, 50, 'PAID'),
(3, 'user3', 203, 'Vet visit', 'Poor', 'Health Check', 1, 1, 150, 'REFUND'),
(4, 'user4', 204, 'Grooming', 'Excellent', 'Grooming', 1, 1, 75, 'PENDING'),
(5, 'user5', 205, 'Training', 'Good', 'Behavior', 1, 1, 200, 'PAID');

SELECT user_id, SUM(TOTAL_PRICE) AS total_price_sum
FROM reserve
GROUP BY user_id;

-- reservlist 테이블
INSERT INTO `reservlist` (`no`, `reserv_no`, `train_no`, `PRICE`, `AMOUNT`, `order_day`)
VALUES
(1, 1, 301, 100, 1, '2023-01-01 10:00:00'),
(2, 2, 302, 50, 1, '2023-02-01 11:00:00'),
(3, 3, 303, 150, 1, '2023-03-01 12:00:00'),
(4, 4, 304, 75, 1, '2023-04-01 13:00:00'),
(5, 5, 305, 200, 1, '2023-05-01 14:00:00');

-- orders 테이블
INSERT INTO `orders` (`order_no`, `date_no`, `STATUS`, `PAYMENT_METHOD`, `pay_check`, `price`)
VALUES
(1, 401, 'PENDING', 'Credit Card', 0, 100),
(2, 402, 'PAID', 'PayPal', 1, 50),
(3, 403, 'REFUND', 'Bank Transfer', 0, 150),
(4, 404, 'PENDING', 'Credit Card', 0, 75),
(5, 405, 'PAID', 'PayPal', 1, 200);

-- img_file 테이블
INSERT INTO `img_file` (`no`, `parent_no`, `parent_table`, `file_name`, `file_path`, `file_size`, `file_code`)
VALUES
(1, 501, 'pets', 'dog.jpg', '/images/dog.jpg', 1024, 'A1'),
(2, 502, 'pets', 'cat.jpg', '/images/cat.jpg', 2048, 'A2'),
(3, 503, 'pets', 'parrot.jpg', '/images/parrot.jpg', 512, 'A3'),
(4, 504, 'pets', 'rabbit.jpg', '/images/rabbit.jpg', 256, 'A4'),
(5, 505, 'pets', 'hamster.jpg', '/images/hamster.jpg', 128, 'A5');

-- reply 테이블
INSERT INTO `reply` (`no`, `board_no2`, `user_id`, `parent_no`, `writer`, `content`)
VALUES
(1, 601, 'user1', '0', 'User1', 'Great post!'),
(2, 602, 'user2', '0', 'User2', 'Very informative.'),
(3, 603, 'user3', '0', 'User3', 'Thanks for sharing.'),
(4, 604, 'user4', '0', 'User4', 'I have a question.'),
(5, 605, 'user5', '0', 'User5', 'Excellent write-up.');

-- notice 테이블
INSERT INTO `board` (`title`, `content`, `file_name`, `reg_date`, `upd_date`, `user_id`, `board_type`)
VALUES
('공지사항 제목 1', '공지사항 내용 1', 'file1.pdf', '2024-06-03 10:00:00', '2024-06-03 10:00:00', 'user1', 1),
('공지사항 제목 2', '공지사항 내용 2', 'file2.pdf', '2024-06-03 11:00:00', '2024-06-03 11:00:00', 'user2', 2),
('공지사항 제목 3', '공지사항 내용 3', 'file3.pdf', '2024-06-03 12:00:00', '2024-06-03 12:00:00', 'user3', 3),
('공지사항 제목 4', '공지사항 내용 4', 'file4.pdf', '2024-06-03 13:00:00', '2024-06-03 13:00:00', 'user4', 1),
('공지사항 제목 5', '공지사항 내용 5', 'file5.pdf', '2024-06-03 14:00:00', '2024-06-03 14:00:00', 'user5', 2);

INSERT INTO `board` (`title`, `content`, `file_name`, `reg_date`, `upd_date`, `user_id`, `board_type`)
SELECT `title`, `content`, `file_name`, `reg_date`, `upd_date`, `user_id`, `board_type`
FROM board;

-- board 테이블
INSERT INTO `board` (`board_no`, `user_id`, `title`, `content`)
VALUES
(1, 'user1', 'Welcome', 'Admin', 'Welcome to our community!'),
(2, 'user2', 'Guidelines', 'Admin', 'Please read the guidelines.'),
(3, 'user3', 'Updates', 'Admin', 'Recent updates on the platform.'),
(4, 'user4', 'Events', 'Admin', 'Upcoming events for members.'),
(5, 'user5', 'Support', 'Admin', 'How to contact support.');





INSERT INTO board( board_no,title, user_id, content)
VALUES ( 1, 안녕, user1, 내용);

INSERT INTO `board` (`board_no`, `user_id`, `title`, `content`)
VALUES()

SELECT *
FROM board






-- review 테이블
INSERT INTO `review` (`review_no`, `user_id`, `rating`, `content`)
VALUES
(1, 'user1', 5, 'Excellent service.'),
(2, 'user2', 4, 'Very good.'),
(3, 'user3', 3, 'Average experience.'),
(4, 'user4', 2, 'Not satisfied.'),
(5, 'user5', 1, 'Poor service.');

-- user_auth 테이블
INSERT INTO `user_auth` (`auth_no`, `user_id`, `auth`)
VALUES
(1, 'user1', 'ADMIN'),
(2, 'user2', 'USER'),
(3, 'user3', 'USER'),
(4, 'user4', 'USER'),
(5, 'user5', 'USER');
(5, 'user6', 'USER');

-- schedule 테이블
INSERT INTO `schedule` (`no`, `user_id`, `trainer_no`, `title`, `content`, `schedule_date`)
VALUES
(1, 'user1', 701, 'Meeting', 'Discuss training schedule.', '2023-06-01 09:00:00'),
(2, 'user2', 702, 'Training', 'Basic obedience training.', '2023-06-02 10:00:00'),
(3, 'user3', 703, 'Consultation', 'Health check-up.', '2023-06-03 11:00:00'),
(4, 'user4', 704, 'Exercise', 'Daily exercise routine.', '2023-06-04 12:00:00'),
(5, 'user5', 705, 'Grooming', 'Regular grooming session.', '2023-06-05 13:00:00');

-- career 테이블
INSERT INTO `career` (`no`, `user_id`, `name`, `reg_date`, `upd_date`) VALUES
(1, 'user1', 'Career 1', '2024-06-01 10:00:00', '2024-06-01 10:00:00'),
(2, 'user2', 'Career 2', '2024-06-02 11:00:00', '2024-06-02 11:00:00'),
(3, 'user3', 'Career 3', '2024-06-03 12:00:00', '2024-06-03 12:00:00'),
(4, 'user4', 'Career 4', '2024-06-04 13:00:00', '2024-06-04 13:00:00'),
(5, 'user5', 'Career 5', '2024-06-05 14:00:00', '2024-06-05 14:00:00');


-- certificate 테이블
INSERT INTO `certificate` (`no`, `user_id`, `name`)
VALUES
(1, 'user1', 'Certified Vet'),
(2, 'user2', 'Certified Trainer'),
(3, 'user3', 'Certified Groomer'),
(4, 'user4', 'Certified Behaviorist'),
(5, 'user5', 'Certified Nutritionist');

-- trainer 테이블
INSERT INTO `trainer` (`no`, `user_id`, `name`, `gender`, `birth`, `address`, `content`, `phone`, `mail`)
VALUES
(1, 'user1', 'John Doe', 'Male', '1980-01-01', '123 Street', 'Expert in training.', 1234567890, 'john@example.com'),
(2, 'user2', 'Jane Smith', 'Female', '1985-02-02', '456 Avenue', 'Specializes in obedience.', 2345678901, 'jane@example.com'),
(3, 'user3', 'Bob Brown', 'Male', '1990-03-03', '789 Boulevard', 'Grooming expert.', 3456789012, 'bob@example.com'),
(4, 'user4', 'Alice Green', 'Female', '1995-04-04', '101 Parkway', 'Behavioral specialist.', 4567890123, 'alice@example.com'),
(5, 'user5', 'Charlie Black', 'Male', '2000-05-05', '202 Lane', 'Nutrition advisor.', 5678901234, 'charlie@example.com');

-- users 테이블
INSERT INTO `users` (`user_id`, `user_no`, `pet_no`, `no`, `name`, `password`, `birth`, `address`, `mail`, `phone`, `gender`, `enable`, `role`)
VALUES
('user1', 1, 1, 1, 'John Doe', 'password1', '1980-01-01', '123 Street', 'john@example.com', '1234567890', 'Male', 1, 1),
('user2', 2, 2, 2, 'Jane Smith', 'password2', '1985-02-02', '456 Avenue', 'jane@example.com', '2345678901', 'Female', 1, 2),
('user3', 3, 3, 3, 'Bob Brown', 'password3', '1990-03-03', '789 Boulevard', 'bob@example.com', '3456789012', 'Male', 1, 1),
('user4', 4, 4, 4, 'Alice Green', 'password4', '1995-04-04', '101 Parkway', 'alice@example.com', '4567890123', 'Female', 1, 2),
('user5', 5, 5, 5, 'Charlie Black', 'password5', '2000-05-05', '202 Lane', 'charlie@example.com', '5678901234', 'Male', 1, 1);
-- 바뀌기 전 샘플데이터
INSERT INTO `users` (`user_id`, `password`, `name`, `birth`, `gender`, `address`, `mail`, `phone`, `reg_date`, `upd_date`, `role`, `enabled`) 
VALUES ('user3', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),1, 1);

-- PRODUCTS 테이블
INSERT INTO `PRODUCTS` (`no`, `no2`, `NAME`, `CAREGORY`, `DESCRIPTION`, `PRICE`)
VALUES
(1, 101, 'Dog Food', 'Pet Supplies', 'High-quality dog food.', 20),
(2, 102, 'Cat Litter', 'Pet Supplies', 'Clumping cat litter.', 15),
(3, 103, 'Bird Cage', 'Pet Accessories', 'Spacious bird cage.', 50),
(4, 104, 'Hamster Wheel', 'Pet Toys', 'Durable hamster wheel.', 10),
(5, 105, 'Fish Tank', 'Pet Accessories', 'Large fish tank.', 100);

-- CATEGORIES 테이블
INSERT INTO `CATEGORIES` (`no`, `CODE`, `NAME`, `SEQ`)
VALUES
(1, 'CAT1', 'Food', 1),
(2, 'CAT2', 'Toys', 2),
(3, 'CAT3', 'Accessories', 3),
(4, 'CAT4', 'Supplies', 4),
(5, 'CAT5', 'Health', 5);

-- CANCELLATIONS 테이블
INSERT INTO `CANCELLATIONS` (`NO`, `date_no`, `STATUS`, `PAYMENT_METHOD`, `pay_check`, `type`, `REASON`, `REFUNDED_AMOUNT`, `IS_CONFIRMED`, `IS_REFUND`, `ACCOUNT_NUMBER`, `BANK_NAME`, `DEPOSITOR`, `CANCELED_AT`, `COMPLETED_AT`)
VALUES
(1, 901, 'PENDING', 'Credit Card', 0, 'REFUND', 'Item damaged.', 20, 1, 1, '123456', 'Bank A', 'John Doe', '2023-01-01 10:00:00', '2023-01-02 10:00:00'),
(2, 902, 'PAID', 'PayPal', 1, 'REFUND', 'Wrong item.', 15, 1, 1, '654321', 'Bank B', 'Jane Smith', '2023-02-01 11:00:00', '2023-02-02 11:00:00'),
(3, 903, 'REFUND', 'Bank Transfer', 0, 'REFUND', 'Not as described.', 50, 1, 1, '987654', 'Bank C', 'Bob Brown', '2023-03-01 12:00:00', '2023-03-02 12:00:00'),
(4, 904, 'PENDING', 'Credit Card', 0, 'REFUND', 'Changed mind.', 10, 1, 1, '456789', 'Bank D', 'Alice Green', '2023-04-01 13:00:00', '2023-04-02 13:00:00'),
(5, 905, 'PAID', 'PayPal', 1, 'REFUND', 'Better price found.', 100, 1, 1, '321456', 'Bank E', 'Charlie Black', '2023-05-01 14:00:00', '2023-05-02 14:00:00');

-- CARTS 테이블
INSERT INTO `CARTS` (`no`, `user_id`, `no2`, `ORDER_DAY`)
VALUES
(1, 'user1', 101, '2023-01-01 10:00:00'),
(2, 'user2', 102, '2023-02-01 11:00:00'),
(3, 'user3', 103, '2023-03-01 12:00:00'),
(4, 'user4', 104, '2023-04-01 13:00:00'),
(5, 'user5', 105, '2023-05-01 14:00:00');
