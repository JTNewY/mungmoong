-- Active: 1717835597775@@127.0.0.1@3306@mypet
-- users
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


-- trainer
TRUNCATE trainer;
INSERT INTO `trainer` (`user_id`, `name`, `gender`, `birth`, `address`, `content`, `phone`, `mail`)
VALUES 
    ('partner2', '이태원', '남성', '1985-05-05', '서울시 강남구 테헤란로 123', '트레이너 경력 10년', 1012345678, 'partner@example.com');
INSERT INTO `trainer` (`user_id`, `name`, `gender`, `birth`, `address`, `content`, `phone`, `mail`)
VALUES 
    ('partner3', '강형욱', '남성', '1985-05-05', '서울시 강남구 테헤란로 123', '트레이너 경력 20년', 1012345678, 'partner@example.com');


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
    ('partner3', 2, '기본 복종 훈련', '2020-01-01 10:00:00', '2020-01-01 10:00:00'),
    ('partner3', 2, '사회화 훈련', '2020-06-01 10:00:00', '2020-06-01 10:00:00'),
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