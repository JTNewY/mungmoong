-- Active: 1717838232505@@127.0.0.1@3306@mypet
INSERT INTO `board` (`title`, `content`, `file_name`, `reg_date`, `user_id`, `board_type`) VALUES
('첫 번째 게시물', '첫 번째 게시물의 내용입니다.', 'file1.jpg', NOW(), 'user1', 1),
('두 번째 게시물', '두 번째 게시물의 내용입니다.', 'file2.jpg', NOW(), 'user2', 2),
('세 번째 게시물', '세 번째 게시물의 내용입니다.', NULL, NOW(), 'user3', 1),
('네 번째 게시물', '네 번째 게시물의 내용입니다.', 'file3.jpg', NOW(), 'user1', 3),
('다섯 번째 게시물', '다섯 번째 게시물의 내용입니다.', NULL, NOW(), 'user2', 2);


INSERT INTO `board` (`title`, `content`, `file_name`, `reg_date`, `user_id`, `board_type`)
SELECT (`title`, `content`, `file_name`, `reg_date`, `user_id`, `board_type`)
FROM board;

DROP TABLE board;
CREATE TABLE `board` (
	`board_no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`title`	VARCHAR(100)	NOT NULL,
	`writer`	VARCHAR(100)	NULL,
	`content`	VARCHAR(1000)	NULL,
	`file_no`	VARCHAR(100)	NULL,
	`board_type`	INT	NULL,
	`reg_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO `board` (
    `board_no`, `user_id`, `title`, `writer`, `content`, `file_no`, `board_type`, `reg_date`, `upd_date`
) VALUES
    (1, 'user001', 'Welcome to Wonderland', 'Alice', 'Welcome to our new members!', 'file001', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'user002', 'Meeting Notice', 'Bob', 'There will be a meeting next Monday.', 'file002', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'user003', 'Project Update', 'Charlie', 'The project is on track for completion.', 'file003', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'user004', 'Holiday Announcement', 'Diana', 'The office will be closed for holidays.', 'file004', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'user005', 'Feedback Request', 'Eve', 'Please provide feedback on the new policy.', 'file005', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


CREATE TABLE `review` (
	`review_no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`rating`	INT	NULL,
	`content`	VARCHAR(1000)	NULL,
	`reg_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `user_auth` (
	`auth_no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`auth`	VARCHAR(40)	NULL
);

CREATE TABLE `schedule` (
	`no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`trainer_no`	INT	NOT NULL,
	`title`	VARCHAR(50)	NULL	DEFAULT 휴무,
	`content`	TEXT	NULL,
	`date`	TIMESTAMP	NOT NULL,
	`reg_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `career` (
	`no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`name`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `certificate` (
	`no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`name`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `trainer` (
	`no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`gender`	VARCHAR(50)	NOT NULL,
	`birth`	VARCHAR(50)	NOT NULL,
	`address`	VARCHAR(150)	NULL,
	`content`	TEXT	NULL,
	`phone`	INT	NULL,
	`mail`	VARCHAR(50)	NULL,
	`reg_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `users` (
	`user_id`	VARCHAR(100)	NOT NULL,
	`user_no`	INT	NOT NULL,
	`pet_no`	INT	NOT NULL,
	`no`	INT	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`password`	VARCHAR(100)	NOT NULL,
	`birth`	VARCHAR(50)	NOT NULL,
	`address`	VARCHAR(150)	NULL,
	`mail`	VARCHAR(50)	NULL,
	`phone`	VARCHAR(50)	NULL,
	`gender`	VARCHAR(50)	NULL,
	`enable`	INT	NULL,
	`role`	INT	NULL,
	`reg_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `PRODUCTS` (
	`ID`	INT	NOT NULL,
	`content`	text	NULL,
	`NAME`	VARCHAR(100)	NOT NULL,
	`CAREGORY`	VARCHAR(100)	NULL,
	`DESCRIPTION`	VARCHAR(100)	NULL,
	`PRICE`	INT	NOT NULL,
	`STOCK`	INT	NULL	DEFAULT 0,
	`reg_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `CATEGORIES` (
	`ID`	INT	NOT NULL,
	`CODE`	VARCHAR(100)	NOT NULL,
	`NAME`	VARCHAR(100)	NOT NULL,
	`SEQ`	INT	NOT NULL	DEFAULT 0,
	`reg_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `CANCELLATIONS` (
	`ID`	INT	NOT NULL,
	`date_no`	INT	NOT NULL,
	`STATUS`	ENUM('PENDING','PAID','REFUND')	NOT NULL	DEFAULT 결제대기,결제완료,환불,
	`type`	ENUM('PENDING','PAID','REFUND')	NULL	DEFAULT 취소,
	`REASON`	TEXT	NULL,
	`REFUNDED_AMOUNT`	INT	NOT NULL,
	`IS_CONFIRMED`	TINYINT(1)	NOT NULL	DEFAULT 0,
	`IS_REFUND`	TINYINT(1)	NOT NULL,
	`ACCOUNT_NUMBER`	VARCHAR(100)	NULL,
	`BANK_NAME`	VARCHAR(100)	NULL,
	`DEPOSITOR`	VARCHAR(100)	NULL,
	`CANCELED_AT`	TIMESTAMP	NULL,
	`COMPLETED_AT`	TIMESTAMP	NULL,
	`reg_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `files` (
	`id`	INT	NOT NULL,
	`PARENT_TABLE`	VARCHAR(100)	NOT NULL	DEFAULT 0,
	`PARENT_ID`	INT	NULL,
	`NAME`	VARCHAR(100)	NOT NULL,
	`ORIGIN_NAME`	VARCHAR(100)	NULL,
	`PATH`	VARCHAR(100)	NULL,
	`SIZE`	VARCHAR(100)	NULL,
	`IS_MAIN`	BOOLEAN	NULL,
	`SEQ`	INT	NULL,
	`upd_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`reg_date`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `pet` (
	`no`	INT	NOT NULL,
	`file_no`	INT	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`age`	INT	NOT NULL,
	`petgender`	INT	NOT NULL,
	`character`	VARCHAR(100)	NULL,
	`type`	VARCHAR(50)	NULL,
	`reg_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
	`specialNotices`	VARCHAR(255)	NULL
);

ALTER TABLE `orders` ADD CONSTRAINT `PK_ORDERS` PRIMARY KEY (
	`no`
);

ALTER TABLE `payments` ADD CONSTRAINT `PK_PAYMENTS` PRIMARY KEY (
	`id`
);

ALTER TABLE `img_file` ADD CONSTRAINT `PK_IMG_FILE` PRIMARY KEY (
	`no`
);

ALTER TABLE `reply` ADD CONSTRAINT `PK_REPLY` PRIMARY KEY (
	`no`
);

ALTER TABLE `notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
	`notice_no`
);

ALTER TABLE `board` ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (
	`no`
);

ALTER TABLE `review` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
	`review_no`
);

ALTER TABLE `user_auth` ADD CONSTRAINT `PK_USER_AUTH` PRIMARY KEY (
	`auth_no`
);

ALTER TABLE `schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
	`no`
);

ALTER TABLE `career` ADD CONSTRAINT `PK_CAREER` PRIMARY KEY (
	`no`
);

ALTER TABLE `certificate` ADD CONSTRAINT `PK_CERTIFICATE` PRIMARY KEY (
	`no`
);

ALTER TABLE `trainer` ADD CONSTRAINT `PK_TRAINER` PRIMARY KEY (
	`no`
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `PRODUCTS` ADD CONSTRAINT `PK_PRODUCTS` PRIMARY KEY (
	`ID`
);

ALTER TABLE `CATEGORIES` ADD CONSTRAINT `PK_CATEGORIES` PRIMARY KEY (
	`ID`
);

ALTER TABLE `CANCELLATIONS` ADD CONSTRAINT `PK_CANCELLATIONS` PRIMARY KEY (
	`ID`
);

ALTER TABLE `files` ADD CONSTRAINT `PK_FILES` PRIMARY KEY (
	`id`
);

ALTER TABLE `pet` ADD CONSTRAINT `PK_PET` PRIMARY KEY (
	`no`
);

