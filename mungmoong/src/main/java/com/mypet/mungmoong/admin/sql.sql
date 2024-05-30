-- Active: 1716856468698@@127.0.0.1@3306@mypet
CREATE USER 'joeun'@'localhost' identified BY '123456';

ALTER TABLE users ADD gender VARCHAR(50);
ALTER TABLE users DROP gender CASCADE;

DROP TABLE pet;



SELECT * from users join pet on pet.user_id=users.user_id;

SELECT * from users;





TRUNCATE TABLE pet;
INSERT INTO `pet` (`petname`, `pettype`, `age`, `petgender`, `character`, `reg_date`, `upd_date`, `order_no`, `user_id`) VALUES
('Buddy', 'Dog', 3, 1, 'Friendly and energetic', '2023-01-15 10:30:00', '2023-02-15 10:30:00', 101, 'user123'),
('Mittens', 'Cat', 2, 2, 'Calm and affectionate', '2023-01-20 12:00:00', '2023-02-20 12:00:00', 102, 'user456'),
('Charlie', 'Dog', 4, 1, 'Playful and loyal', '2023-01-25 14:45:00', '2023-02-25 14:45:00', 103, 'user789'),
('Luna', 'Cat', 1, 2, 'Curious and active', '2023-02-01 09:15:00', '2023-03-01 09:15:00', 104, 'user321'),
('Max', 'Dog', 5, 1, 'Protective and brave', '2023-02-10 16:30:00', '2023-03-10 16:30:00', 105, 'user654'),
('Bella', 'Cat', 3, 2, 'Loving and playful', '2023-02-15 11:00:00', '2023-03-15 11:00:00', 106, 'user987'),
('Rocky', 'Dog', 2, 1, 'Adventurous and friendly', '2023-02-20 08:30:00', '2023-03-20 08:30:00', 107, 'user135'),
('Shadow', 'Cat', 4, 2, 'Independent and quiet', '2023-02-25 14:00:00', '2023-03-25 14:00:00', 108, 'user246');




-- Active: 1716856468698@@127.0.0.1@3306@mungmoong

CREATE TABLE `pet` (
	`pet_no`	INT	NOT NULL,
	`petname`	VARCHAR(50)	NOT NULL,
	`pettype` VARCHAR(50)	NOT NULL,
	`age`	INT	NOT NULL,
	`petgender`	INT	NOT NULL,
	`character`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`order_no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL
);

ALTER TABLE `pet` MODIFY COLUMN `pet_no` INT AUTO_INCREMENT PRIMARY KEY;

CREATE TABLE `trainer` (
	`no`			VARCHAR(50)	NOT NULL,	-- 훈련사 번호
	`order_no`		INT			NOT NULL,	-- 결제 번호
	`name`			VARCHAR(50)	NOT NULL,	-- 이름
	`gender`		VARCHAR(50)	NOT NULL,	-- 성별
	`birth`			VARCHAR(50) NOT NULL,	-- 생일
	`mail`			VARCHAR(50)		NULL,	-- 이메일
    `phone`			VARCHAR(50)		NULL,	-- 핸드폰 번호
	`address`		VARCHAR(150)	NULL,	-- 주소
	`reg_date`		TIMESTAMP		NULL,	-- 등록일
	`upd_date`		TIMESTAMP		NULL,	-- 수정일
	`career`		VARCHAR(100)	NULL,	-- 경력
	`certificate`	VARCHAR(100)	NULL,	-- 자격증
	`content`		VARCHAR(1000)	NULL,	-- 소개
	`user_id`		VARCHAR(40)	NOT NULL	-- 회원 아이디
);


CREATE TABLE `reserve` (
	`date_no`	INT	NOT NULL,
	`date_time`	TIMESTAMP	NULL,
	`date_day`	TIMESTAMP	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`trainer_id`	VARCHAR(100)	NOT NULL,
	`order_no`	INT	NOT NULL
);

CREATE TABLE `ordersdetail` (
	`order_id`	INT	NOT NULL,
	`card_no`	VARCHAR(100)	NULL,
	`order_price`	VARCHAR(100)	NULL,
	`price_check`	Boolean	NULL,
	`order_date`	TIMESTAMP	NULL,
	`order_check`	Boolean	NULL,
	`order_no`	INT	NOT NULL
);

CREATE TABLE `orders` (
	`order_no`	INT	NOT NULL,
	`user_check`	BOOLEAN	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`pay_check`	BOOLEAN	NULL,
	`date_no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`order_id`	INT	NOT NULL,
	`trainer_check`	INT	NULL
);

CREATE TABLE `img_file` (
	`no`	INT	NULL,
	`parent_no`	INT	NULL,
	`parent_table`	VARCHAR(100)	NULL,
	`file_name`	VARCHAR(100)	NOT NULL,
	`file_path`	VARCHAR(100)	NULL,
	`file_size`	LONG	NULL,
	`file_code`	VARCHAR(50)	NULL,
	`user_no`	VARCHAR(100)	NOT NULL,
	`reg_date`	DATE	NULL,
	`upd_date`	DATE	NULL
);

CREATE TABLE `reply` (
	`file_no`	INT	NOT NULL,
	`board_no`	INT	NULL,
	`parent_no`	VARCHAR(100)	NULL,
	`writer`	VARCHAR(100)	NULL,
	`content`	VARCHAR(1000)	NULL,
	`upd_date`	TIMESTAMP	NOT NULL,
	`reg_date`	TIMESTAMP	NOT NULL,
	`board_no2`	INT	NOT NULL
);

CREATE TABLE `notice` (
	`notice_no`	INT	NOT NULL,
	`notice_content`	VARCHAR(1000)	NOT NULL,
	`writer`	VARCHAR(50)	NOT NULL,
	`age`	TIMESTAMP	NOT NULL,
	`currer`	TIMESTAMP	NULL
);

CREATE TABLE `board` (
	`board_no`	INT	NOT NULL,
	`title`	VARCHAR(100)	NOT NULL,
	`content`	VARCHAR(1000)	NULL,
	`file_name`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NOT NULL,
	`upd_date`	TIMESTAMP	NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`board_type`	INT	NULL
);

CREATE TABLE `review` (
	`review_no`	VARCHAR(50)	NOT NULL,
	`trainer_id`	VARCHAR(100)	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`rating`	INT	NULL,
	`content`	VARCHAR(1000)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL
);

TRUNCATE TABLE EXISTS  users;
CREATE TABLE `users` (
	`user_id`	VARCHAR(100)	NOT NULL,
	`password`	VARCHAR(100)	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`birth`	TIMESTAMP	NOT NULL,
	`address`	VARCHAR(150)	NULL,
	`mail`	VARCHAR(50)	NULL,
	`phone`	VARCHAR(50)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`role`	INT	NULL,
	`enabled` INT NULL
);



DROP TABLE IF EXISTS user_auth;
CREATE TABLE `user_auth` (
	`auth_no`	INT	PRIMARY KEY AUTO_INCREMENT,
	`user_id`	VARCHAR(100)	NOT NULL,
	`auth`	VARCHAR(40)	NULL
);

CREATE TABLE `schedule` (
	`schedule_no`	VARCHAR(50)	NOT NULL,
	`trainer_no`	VARCHAR(50)	NOT NULL,
	`title`	VARCHAR(50)	NULL ,
	`content`	TEXT	NULL,
	`schedule_date`	TIMESTAMP	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL
);

ALTER TABLE `pet` ADD CONSTRAINT `PK_PET` PRIMARY KEY (
	`pet_no`
);

ALTER TABLE `trainer` ADD CONSTRAINT `PK_TRAINER` PRIMARY KEY (
	`no`
);

ALTER TABLE `reserve` ADD CONSTRAINT `PK_RESERVE` PRIMARY KEY (
	`date_no`
);

ALTER TABLE `ordersdetail` ADD CONSTRAINT `PK_ORDERSDETAIL` PRIMARY KEY (
	`order_id`
);

ALTER TABLE `orders` ADD CONSTRAINT `PK_ORDERS` PRIMARY KEY (
	`order_no`
);

ALTER TABLE `img_file` ADD CONSTRAINT `PK_IMG_FILE` PRIMARY KEY (
	`no`
);

ALTER TABLE `reply` ADD CONSTRAINT `PK_REPLY` PRIMARY KEY (
	`file_no`
);

ALTER TABLE `notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
	`notice_no`
);

ALTER TABLE `board` ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (
	`board_no`
);

ALTER TABLE `review` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
	`review_no`
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `user_auth` ADD CONSTRAINT `PK_USER_AUTH` PRIMARY KEY (
	`auth_no`
);

ALTER TABLE `schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
	`schedule_no`
);

TRUNCATE TABLE users;







INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role, enabled)
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0' ,1);


INSERT INTO user_auth ( auth_no, user_id,  auth )
VALUES (1,'user', 'ROLE_USER' );
INSERT INTO user_auth ( auth_no, user_id,  auth )
VALUES (2, 'user123', 'ROLE_USER' );


INSERT INTO `trainer` (`no`, `order_no`, `name`, `gender`, `birth`, `address`, `reg_date`, `upd_date`, `career`, `certificate`, `content`, `user_id`, `mail`, `phone`)
VALUES
('T001', 1001, '이태원', '남성', '1988-01-01', '서울특별시 강남구 테헤란로 123', '2023-01-15 10:00:00', '2023-05-20 14:30:00', '5년 경력', '공인 전문 도그 트레이너', '긍정적 강화 중심의 개 훈련에 열정적입니다.', 'user123', 'itaewon@example.com', '010-1234-5678'),
('T002', 1002, '오은아', '여성', '1995-01-01', '부산광역시 해운대구 해운대로 456', '2023-02-18 11:00:00', '2023-06-25 16:45:00', '3년 경력', '공인 도그 행동 컨설턴트', '행동 수정 및 기민 훈련 전문.', 'user456', 'oeuna@example.com', '010-2345-6789'),
('T003', 1003, '권혁준', '남성', '1983-01-01', '인천광역시 남동구 인주대로 789', '2023-03-22 09:00:00', '2023-07-15 09:15:00', '10년 경력', '공인 개 피트니스 트레이너', '개 피트니스 및 재활 전문가.', 'user789', 'khj@example.com', '010-3456-7890'),
('T004', 1004, '김재희', '여성', '1991-01-01', '대구광역시 수성구 달구벌대로 321', '2023-04-10 14:00:00', '2023-08-05 11:00:00', '7년 경력', '공인 도그 트레이너 및 행동 전문가', '복종 훈련 및 행동 교정 집중.', 'user101', 'jaehee@example.com', '010-4567-8901'),
('T005', 1005, '정태진', '남성', '1994-01-01', '대전광역시 서구 둔산로 654', '2023-05-05 13:00:00', '2023-09-10 15:30:00', '4년 경력', '공인 서비스 도그 트레이너', '장애인을 위한 서비스 도그 훈련에 전념.', 'user202', 'taejin@example.com', '010-5678-9012');



INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 'user1', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '김조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 )
,( 'user2', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '이조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 )
,( 'user3', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '박조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 )
,( 'user4', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '오조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 )
,( 'user5', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '배조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 )
,( 'user6', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '강조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 )
,( 'user7', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '신조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );

INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 'user123', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '김조은', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );

