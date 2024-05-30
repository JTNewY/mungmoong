
-- Active: 1716798076500@@127.0.0.1@3306@mypet


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


DROP TABLE trainer;

SELECT * FROM trainer;


DROP TABLE IF EXISTS trainer;

CREATE TABLE `trainer` (
	`no`			INT	NOT NULL PRIMARY KEY AUTO_INCREMENT,	-- 훈련사 번호
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
	`content`		TEXT			NULL,	-- 소개
	`user_id`		VARCHAR(100)	NOT NULL-- 회원 아이디
);

CREATE TABLE `career` (
	`no`		INT			NOT NULL, -- 경력 번호
	`trainer_no`	INT		NOT NULL, -- 훈련사 번호
	`name`	VARCHAR(100)		NULL, -- 경력 이름
	`reg_date`	TIMESTAMP		NULL, -- 등록일
	`upd_date`	TIMESTAMP		NULL  -- 수정일
);

CREATE TABLE `certificate` (
	`no`	INT			NOT NULL, -- 자격증 번호
	`trainer_no`	INT	NOT NULL, -- 훈련사 번호
	`file_no`	INT			NULL, -- 파일 번호
	`name`	VARCHAR(100)	NULL, -- 자격증 명
	`reg_date`	TIMESTAMP	NULL, -- 등록일
	`upd_date`	TIMESTAMP	NULL  -- 수정일
);

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
	`gender`		VARCHAR(50)	NOT NULL,	-- 성별
	`address`	VARCHAR(150)	NULL,
	`mail`	VARCHAR(50)	NULL,
	`gender`	VARCHAR(50)	NOT NULL,
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

TRUNCATE TABLE users;


