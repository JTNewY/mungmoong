
-- Active: 1705301251861@@127.0.0.1@3306@mypet


TRUNCATE users;

INSERT INTO `users` (`user_id`, `name`, `password`, `birth`, `address`, `mail`, `phone`, `gender`, `enabled`, `role`, `reg_date`, `upd_date`)
VALUES 
    ('user', '샘플 사용자', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '1990-01-01', '사용자 길 123', 'user@example.com', '010-1234-5678', '남성', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('partner', '샘플 파트너', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '1985-05-05', '파트너 길 456', 'partner@example.com', '010-9876-5432', '여성', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('partner1', '샘플 파트너', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '1985-05-05', '파트너 길 456', 'partner@example.com', '010-9876-5432', '여성', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('partner2', '샘플 파트너', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '1985-05-05', '파트너 길 456', 'partner@example.com', '010-9876-5432', '여성', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('partner3', '샘플 파트너', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '1985-05-05', '파트너 길 456', 'partner@example.com', '010-9876-5432', '여성', 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('admin', '샘플 관리자', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '1975-10-10', '관리자 길 789', 'admin@example.com', '010-5555-5555', '기타', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- user_auth
TRUNCATE user_auth;
INSERT INTO `user_auth` (`user_id`, `auth`)
VALUES 
    ('user', 'ROLE_USER'),
    ('partner', 'ROLE_USER'),
    ('partner', 'ROLE_PARTNER'),
    ('partner1', 'ROLE_USER'),
    ('partner1', 'ROLE_PARTNER'),
    ('partner2', 'ROLE_USER'),
    ('partner2', 'ROLE_PARTNER'),
    ('partner3', 'ROLE_USER'),
    ('partner3', 'ROLE_PARTNER'),
    ('admin', 'ROLE_USER'),
    ('admin', 'ROLE_ADMIN');


-- pet
TRUNCATE pet;
INSERT INTO `pet` ( `petname`, `age`, `petgender`, `character`, `type`, `user_id`, `reg_date`, `upd_date`)
VALUES 
    ('멍멍이', 3, 1, '활발함', '골든 리트리버', 'user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('망망이', 2, 0, '사랑스러움', '말티즈', 'user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('뭉뭉이', 4, 1, '친근함', '비숑 프리제', 'user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- trainer
TRUNCATE trainer;
INSERT INTO `trainer` (`user_id`, `name`, `gender`, `birth`, `address`, `content`, `phone`, `mail`)
VALUES 
    ('partner2', '이태원', '남성', '1985-05-05', '서울시 강남구 테헤란로 123', '트레이너 경력 10년', 1012345678, 'partner@example.com');
INSERT INTO `trainer` (`user_id`, `name`, `gender`, `birth`, `address`, `content`, `phone`, `mail`)
VALUES 
    ('partner3', '강형욱', '남성', '1985-05-05', '서울시 강남구 테헤란로 123', '트레이너 경력 20년', 1012345678, 'partner@example.com');
INSERT INTO `trainer` (`user_id`, `name`, `gender`, `birth`, `address`, `content`, `phone`, `mail`)
VALUES 
    ('partner', '오은아', '여성', '1985-05-05', '서울시 강남구 테헤란로 123', '트레이너 경력 20년', 1012345678, 'partner@example.com');


-- career
TRUNCATE career;
-- partner2 의 경력
INSERT INTO `career` (`user_id`, `trainer_no`, `name`, `reg_date`, `upd_date`)
VALUES 
    ('partner2', 1, '기본 복종 훈련', '2020-01-01 10:00:00', '2020-01-01 10:00:00'),
    ('partner2', 1, '사회화 훈련', '2020-06-01 10:00:00', '2020-06-01 10:00:00'),
    ('partner2', 1, '명령어 훈련', '2021-01-01 10:00:00', '2021-01-01 10:00:00'),
    ('partner2', 1, '장애물 극복 훈련', '2021-06-01 10:00:00', '2021-06-01 10:00:00'),
    ('partner2', 1, '특수 목적 훈련', '2022-01-01 10:00:00', '2022-01-01 10:00:00');

-- partner3 의 경력
INSERT INTO `career` (`user_id`, `trainer_no`, `name`, `reg_date`, `upd_date`)
VALUES 
    ('partner', 2, '기본 복종 훈련', '2020-01-01 10:00:00', '2020-01-01 10:00:00'),
    ('partner', 2, '사회화 훈련', '2020-06-01 10:00:00', '2020-06-01 10:00:00'),
    ('partner3', 2, '명령어 훈련', '2021-01-01 10:00:00', '2021-01-01 10:00:00'),
    ('partner3', 2, '장애물 극복 훈련', '2021-06-01 10:00:00', '2021-06-01 10:00:00'),
    ('partner3', 2, '특수 목적 훈련', '2022-01-01 10:00:00', '2022-01-01 10:00:00');



-- certificate
TRUNCATE certificate;
INSERT INTO `certificate` (`trainer_no`, `user_id`, `name`, `reg_date`, `upd_date`)
VALUES 
    (1,'partner2', '기본 강아지 훈련사 자격증', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2,'partner2', '고급 강아지 훈련사 자격증', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3,'partner2', '전문 강아지 훈련사 자격증', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4,'partner3', '기본 강아지 훈련사 자격증', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5,'partner3', '고급 강아지 훈련사 자격증', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- img_file
TRUNCATE img_file;

-- [훈련사] 소개 프로필


-- [훈련사] 자격증
INSERT INTO `img_file` (parent_no, parent_table, file_name, file_path, file_size, file_code ) 
VALUES 
(1,'certificate','d31f97b8-4f4f-471a-9de0-9f6dd188f226_자격증1.png','C:/upload/d31f97b8-4f4f-471a-9de0-9f6dd188f226_자격증1.png','1056471','0'),
(2,'certificate','1d5b15ae-4387-450b-9068-16ecd1c11bcc_자격증2.png','C:/upload/1d5b15ae-4387-450b-9068-16ecd1c11bcc_자격증2.png','292854','0'),
(3,'certificate','60a5544e-f6d4-44e4-bd04-75026d51177c_자격증3.jpg','C:/upload/60a5544e-f6d4-44e4-bd04-75026d51177c_자격증3.jpg','54331','0'),
(4,'certificate','4102263f-fda8-45a9-9fe7-1e58594f486b_자격증4.jpg','C:/upload/4102263f-fda8-45a9-9fe7-1e58594f486b_자격증4.jpg','143184','0'),
(5,'certificate','0231e95b-9018-460a-95db-7ebcc69f7ec3_자격증5.png','C:/upload/0231e95b-9018-460a-95db-7ebcc69f7ec3_자격증5.png','19617','0');



-- products
TRUNCATE products;
INSERT INTO `PRODUCTS` (`TRAINER_NO`, `ID`, `NAME`, `DESCRIPTION`, `CONTENT`, `PRICE`, `CREATED_AT`, `UPDATED_AT`)
VALUES 
    (1, '1', '기본 복종 훈련 패키지', '기본적인 복종 훈련 패키지', '강아지의 기본적인 복종 훈련을 위한 패키지입니다.', 150000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, '2', '사회화 훈련 패키지', '강아지 사회화 훈련 패키지', '강아지가 다른 강아지와 사람들과 잘 어울릴 수 있도록 돕는 사회화 훈련 패키지입니다.', 200000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, '3', '명령어 훈련 패키지', '기본 명령어 훈련 패키지', '앉아, 기다려 등 기본 명령어 훈련을 위한 패키지입니다.', 180000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, '4', '장애물 극복 훈련 패키지', '장애물 극복 훈련 패키지', '강아지가 다양한 장애물을 극복할 수 있도록 돕는 훈련 패키지입니다.', 220000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, '5', '특수 목적 훈련 패키지', '특수 목적을 위한 훈련 패키지', '강아지가 특정한 목적을 위해 훈련될 수 있도록 돕는 패키지입니다.', 250000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, '6', '명령어 훈련 패키지', '기본 명령어 훈련 패키지', '앉아, 기다려 등 기본 명령어 훈련을 위한 패키지입니다.', 180000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, '7', '장애물 극복 훈련 패키지', '장애물 극복 훈련 패키지', '강아지가 다양한 장애물을 극복할 수 있도록 돕는 훈련 패키지입니다.', 220000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, '8', '특수 목적 훈련 패키지', '특수 목적을 위한 훈련 패키지', '강아지가 특정한 목적을 위해 훈련될 수 있도록 돕는 패키지입니다.', 250000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



-- schedule
TRUNCATE schedule;
INSERT INTO `schedule` (`user_id`, `trainer_no`, `title`, `content`, `schedule_date`)
VALUE
('partner2', 1, '일정 1', '일정 1의 내용입니다.', '2024-06-10 09:00:00'),
('partner2', 1, '일정 2', '일정 2의 내용입니다.', '2024-06-11 10:30:00'),
('partner2', 1, '일정 3', '일정 3의 내용입니다.', '2024-06-12 14:00:00'),
('partner2', 1, '일정 4', '일정 4의 내용입니다.', '2024-06-13 11:00:00'),
('partner2', 1, '일정 5', '일정 5의 내용입니다.', '2024-06-14 16:30:00'),
('partner2', 1, '일정 6', '일정 6의 내용입니다.', '2024-06-15 08:00:00'),
('partner2', 1, '일정 7', '일정 7의 내용입니다.', '2024-06-16 10:00:00'),
('partner2', 1, '일정 8', '일정 8의 내용입니다.', '2024-06-17 13:30:00'),
('partner2', 1, '일정 9', '일정 9의 내용입니다.', '2024-06-18 15:00:00'),
('partner2', 1, '일정 10', '일정 10의 내용입니다.', '2024-06-19 09:30:00'),
('partner2', 1, '일정 11', '일정 11의 내용입니다.', '2024-06-20 12:00:00'),
('partner2', 1, '일정 12', '일정 12의 내용입니다.', '2024-06-21 14:30:00'),
('partner2', 1, '일정 13', '일정 13의 내용입니다.', '2024-06-22 10:00:00'),
('partner2', 1, '일정 14', '일정 14의 내용입니다.', '2024-06-23 11:30:00'),
('partner2', 1, '일정 15', '일정 15의 내용입니다.', '2024-06-24 16:00:00'),
('partner2', 1, '일정 16', '일정 16의 내용입니다.', '2024-06-25 09:00:00'),
('partner2', 1, '일정 17', '일정 17의 내용입니다.', '2024-06-26 10:30:00'),
('partner2', 1, '일정 18', '일정 18의 내용입니다.', '2024-06-27 15:00:00'),
('partner2', 1, '일정 19', '일정 19의 내용입니다.', '2024-06-28 08:30:00'),
('partner2', 1, '일정 20', '일정 20의 내용입니다.', '2024-06-29 12:00:00');





INSERT INTO `board` (`user_id`, `title`, `writer`, `content`, `file_no`, `board_type`, `reg_date`, `upd_date`) VALUES
('user', 'Sample Title 1', 'Writer 1', 'Sample content 1', 'file1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 'Sample Title 2', 'Writer 2', 'Sample content 2', 'file2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 'Sample Title 3', 'Writer 3', 'Sample content 3', 'file3', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 'Sample Title 4', 'Writer 4', 'Sample content 4', 'file4', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 'Sample Title 5', 'Writer 5', 'Sample content 5', 'file5', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner1', 'Sample Title 6', 'Writer 6', 'Sample content 6', 'file6', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner1', 'Sample Title 7', 'Writer 7', 'Sample content 7', 'file7', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner1', 'Sample Title 8', 'Writer 8', 'Sample content 8', 'file8', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner1', 'Sample Title 9', 'Writer 9', 'Sample content 9', 'file9', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner1', 'Sample Title 10', 'Writer 10', 'Sample content 10', 'file10', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner1', 'Sample Title 11', 'Writer 11', 'Sample content 11', 'file11', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner2', 'Sample Title 12', 'Writer 12', 'Sample content 12', 'file12', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner2', 'Sample Title 13', 'Writer 13', 'Sample content 13', 'file13', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner2', 'Sample Title 14', 'Writer 14', 'Sample content 14', 'file14', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner2', 'Sample Title 15', 'Writer 15', 'Sample content 15', 'file15', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner2', 'Sample Title 16', 'Writer 16', 'Sample content 16', 'file16', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner2', 'Sample Title 17', 'Writer 17', 'Sample content 17', 'file17', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner', 'Sample Title 18', 'Writer 18', 'Sample content 18', 'file18', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner', 'Sample Title 19', 'Writer 19', 'Sample content 19', 'file19', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('partner', 'Sample Title 20', 'Writer 20', 'Sample content 20', 'file20', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



INSERT INTO `orders` (`ID`, `PRODUCT_ID`, `USER_ID`, `pet_no`, `trainer_no`, `address`, `resDate`, `MEMO`, `TITLE`, `PRICE`, `STATUS`, `meaning`)
VALUES
(UUID(), 'P001', 'user', 1, 101, '123 Pet St, Pet City', '2024-06-11 10:00:00', 'Please be gentle', 'Dog Training', 100, 'pending', 0),
(UUID(), 'P002', 'partner', 2, 102, '456 Animal Ave, Pet City', '2024-06-12 11:00:00', 'Special care needed', 'Cat Training', 150, 'paid', 1),
(UUID(), 'P003', 'partner1', 3, 103, '789 Pet Lane, Pet City', '2024-06-13 12:00:00', 'No special requests', 'Bird Training', 120, 'pending', 0),
(UUID(), 'P004', 'partner2', 4, 104, '321 Pet Circle, Pet City', '2024-06-14 13:00:00', 'Bring extra toys', 'Rabbit Training', 130, 'under_review', 2),
(UUID(), 'P005', 'partner3', 5, 105, '654 Pet Square, Pet City', '2024-06-15 14:00:00', 'Handle with care', 'Dog Training', 110, 'training_completed', 3),
(UUID(), 'P006', 'admin', 6, 106, '987 Animal Blvd, Pet City', '2024-06-16 15:00:00', 'Extra attention needed', 'Cat Training', 140, 'cancelled', 4),
(UUID(), 'P007', 'user', 7, 107, '135 Pet Rd, Pet City', '2024-06-17 16:00:00', 'No special requests', 'Bird Training', 115, 'pending', 0),
(UUID(), 'P008', 'partner', 8, 108, '246 Animal Way, Pet City', '2024-06-18 17:00:00', 'Feed before training', 'Rabbit Training', 160, 'paid', 1),
(UUID(), 'P009', 'partner1', 9, 109, '357 Pet Alley, Pet City', '2024-06-19 18:00:00', 'Sensitive pet', 'Dog Training', 170, 'under_review', 2),
(UUID(), 'P010', 'partner2', 10, 110, '468 Animal Park, Pet City', '2024-06-20 19:00:00', 'Allergies to note', 'Cat Training', 180, 'training_completed', 3);

