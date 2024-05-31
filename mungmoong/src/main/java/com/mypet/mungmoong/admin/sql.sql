
-- Active: 1714463131843@@127.0.0.1@3306@mypet

TRUNCATE TABLE users;
TRUNCATE TABLE pet;
TRUNCATE TABLE trainer;
TRUNCATE TABLE career;
TRUNCATE TABLE certificate;
TRUNCATE TABLE schedule;
TRUNCATE TABLE reserve;
TRUNCATE TABLE ordersdetail;
TRUNCATE TABLE orders;
TRUNCATE TABLE img_file;
TRUNCATE TABLE reply;
TRUNCATE TABLE notice;
TRUNCATE TABLE board;
TRUNCATE TABLE review;
TRUNCATE TABLE user_auth;



DROP TABLE reserve;
DROP TABLE ordersdetail;
DROP TABLE orders;
DROP TABLE img_file;
DROP TABLE reply;
DROP TABLE notice;
DROP TABLE board;
DROP TABLE review;
DROP TABLE user_auth;
DROP TABLE certificate;
DROP TABLE schedule;
DROP TABLE career;
DROP TABLE trainer;
DROP TABLE pet;
DROP TABLE users;

CREATE TABLE `pet` (
	`pet_no`	INT	PRIMARY KEY AUTO_INCREMENT,
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

ALTER TABLE career DROP FOREIGN KEY fk_career_trainer_no;
ALTER TABLE certificate DROP FOREIGN KEY fk_certificate_trainer_no;


DROP TABLE trainer;
DROP TABLE career;
DROP TABLE certificate;

SELECT * FROM trainer;
SELECT * FROM career;
SELECT * FROM certificate;


DROP TABLE IF EXISTS trainer;

CREATE TABLE `trainer` (
	`no`			INT	PRIMARY KEY AUTO_INCREMENT,	-- 훈련사 번호
	`name`			VARCHAR(50)	NOT NULL,	-- 이름
	`gender`		VARCHAR(50)	NOT NULL,	-- 성별
	`birth`			VARCHAR(50) NOT NULL,	-- 생일
	`mail`			VARCHAR(50)		NULL,	-- 이메일
    `phone`			VARCHAR(50)		NULL,	-- 핸드폰 번호
	`address`		VARCHAR(150)	NULL,	-- 주소
	`reg_date`		TIMESTAMP		NULL,	-- 등록일
	`upd_date`		TIMESTAMP		NULL,	-- 수정일
	`content`		TEXT			NULL,	-- 소개
	`user_id`		VARCHAR(100)	NOT NULL-- 회원 아이디
);

CREATE TABLE `career` (
	`no`			INT		PRIMARY KEY AUTO_INCREMENT, -- 경력 번호
	`trainer_no`	INT		NOT NULL, -- 훈련사 번호
	`name`	VARCHAR(100)		NULL, -- 경력 이름
	`reg_date`	TIMESTAMP		NULL, -- 등록일
	`upd_date`	TIMESTAMP		NULL  -- 수정일
);

CREATE TABLE `certificate` (
	`no`	INT		 	PRIMARY KEY AUTO_INCREMENT, -- 자격증 번호
	`trainer_no`	INT	NOT NULL, -- 훈련사 번호
	`name`	VARCHAR(100)	NULL, -- 자격증 명
	`reg_date`	TIMESTAMP	NULL, -- 등록일   
	`upd_date`	TIMESTAMP	NULL  -- 수정일
);

ALTER TABLE career
ADD CONSTRAINT fk_career_trainer_no FOREIGN KEY (trainer_no) REFERENCES trainer(no);

ALTER TABLE certificate
ADD CONSTRAINT fk_certificate_trainer_no FOREIGN KEY (trainer_no) REFERENCES trainer(no);




CREATE TABLE `schedule` (
	`schedule_no`	INT		NOT NULL, -- 스케쥴 번호
	`trainer_no`	INT		NOT NULL, -- 훈련사 번호
	`title`	VARCHAR(50)			NULL, -- 이게 필요한지, 모달로 띄울건지 고민, 일단 보류 
	`content`	TEXT			NULL, -- 내용
	`schedule_date`	TIMESTAMP	NULL, -- 날짜
	`reg_date`	TIMESTAMP		NULL, -- 등록일
	`upd_date`	TIMESTAMP		NULL  -- 수정일
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


DROP TABLE IF EXISTS `img_file`;

DROP TABLE IF EXISTS `img_file`;

CREATE TABLE `img_file` (
	`no`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`parent_no`	INT	NOT NULL,
	`parent_table`	VARCHAR(100)	NOT NULL,
	`file_name`	VARCHAR(100)	NOT NULL,
	`file_path`	VARCHAR(100)	NOT NULL,
	`file_size`	LONG	NULL,
	`file_code`	VARCHAR(50)	NULL,
	`reg_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
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

DROP TABLE users;
TRUNCATE TABLE EXISTS  users;
CREATE TABLE `users` (
	`user_id`	VARCHAR(100)	NOT NULL,
	`password`	VARCHAR(100)	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`birth`	TIMESTAMP	NOT NULL,
	`gender`		VARCHAR(50)	NOT NULL,	-- 성별
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


ALTER TABLE `pet` MODIFY COLUMN `pet_no` INT AUTO_INCREMENT PRIMARY KEY;


ALTER TABLE `trainer` ADD CONSTRAINT `PK_TRAINER` PRIMARY KEY (
	`trainer_no`
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


INSERT INTO `trainer` (`name`, `gender`, `birth`, `mail`, `phone`, `address`, `reg_date`, `upd_date`, `content`, `user_id`)
VALUES 
('Kim Minsoo', 'Male', '1985-06-15', 'minsoo.kim@example.com', '010-1234-5678', '123 Gangnam-daero, Gangnam-gu, Seoul', NOW(), NOW(), 'Experienced dog trainer specializing in obedience training.', 'user123'),
('Lee Hyori', 'Female', '1990-08-25', 'hyori.lee@example.com', '010-8765-4321', '456 Apgujeong-ro, Gangnam-gu, Seoul', NOW(), NOW(), 'Passionate about training dogs with positive reinforcement methods.', 'user124'),
('Park Jisoo', 'Female', '1987-12-30', 'jisoo.park@example.com', '010-1122-3344', '789 Itaewon-ro, Yongsan-gu, Seoul', NOW(), NOW(), 'Specializes in agility training and behavioral correction.', 'user125'),
('Choi Junho', 'Male', '1992-04-05', 'junho.choi@example.com', '010-9988-7766', '101 Mapo-daero, Mapo-gu, Seoul', NOW(), NOW(), 'Expert in service dog training and assistance dog programs.', 'user126'),
('Jung Hana', 'Female', '1995-11-20', 'hana.jung@example.com', '010-3344-5566', '202 Yeouido-dong, Yeongdeungpo-gu, Seoul', NOW(), NOW(), 'Enthusiastic about helping dogs overcome anxiety and fear.', 'user127');



TRUNCATE TABLE users;
INSERT INTO `users` (`user_id`, `password`, `name`, `birth`, `gender`, `address`, `mail`, `phone`, `reg_date`, `upd_date`, `role`, `enabled`) VALUES
('user1', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', 'Alice Johnson', '1990-01-01 00:00:00', 'Female', '123 Maple Street', 'alice@example.com', '123-456-7890', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
('user2', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', 'Bob Smith', '1985-02-02 00:00:00', 'Male', '456 Oak Avenue', 'bob@example.com', '234-567-8901', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
('user3', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', 'Carol White', '1992-03-03 00:00:00', 'Female', '789 Pine Lane', 'carol@example.com', '345-678-9012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
('user4', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', 'David Brown', '1988-04-04 00:00:00', 'Male', '101 Birch Road', 'david@example.com', '456-789-0123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
('user5', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', 'Eve Davis', '1995-05-05 00:00:00', 'Female', '202 Cedar Boulevard', 'eve@example.com', '567-890-1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1);
INSERT INTO `user_auth`(`user_id`, `auth`)
VALUES 	( 'user1', 'ROLE_USER' )
		,( 'user2', 'ROLE_USER' )
		,( 'user3', 'ROLE_USER' )
		,( 'user4', 'ROLE_USER' )
		,( 'user5', 'ROLE_USER' );

INSERT INTO pet (`petname`, `pettype`, `age`, `petgender`, `character`, `reg_date`, `upd_date`, `order_no`, `user_id`) 
VALUES	('Buddy', 'Dog', 3, 1, 'Friendly and energetic', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 'user1')
		,('Mittens', 'Cat', 2, 0, 'Calm and affectionate', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 'user1')
		,('Rex', 'Dog', 4, 1, 'Loyal and protective', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 'user1')
		,('Whiskers', 'Cat', 1, 0, 'Playful and curious', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 'user2')
		,('Spike', 'Dog', 5, 1, 'Brave and bold', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 'user2')
		,('Fluffy', 'Rabbit', 2, 0, 'Gentle and shy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 'user2')
		,('Bella', 'Dog', 3, 0, 'Friendly and active', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 'user3')
		,('Simba', 'Cat', 3, 1, 'Independent and strong', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 'user3')
		,('Nibbles', 'Hamster', 1, 1, 'Curious and quick', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9, 'user3')
		,('Oscar', 'Fish', 1, 1, 'Calm and silent', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 'user4')
		,('Lola', 'Bird', 2, 0, 'Chirpy and bright', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11, 'user4')
		,('Max', 'Dog', 4, 1, 'Loyal and friendly', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12, 'user4')
		,('Charlie', 'Dog', 2, 1, 'Playful and happy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13, 'user5')
		,('Coco', 'Cat', 1, 0, 'Mischievous and curious', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 14, 'user5')
		,('Pepper', 'Parrot', 3, 1, 'Talkative and colorful', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15, 'user5');

