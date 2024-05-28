
CREATE TABLE `pet` (
	`pet_no`	INT	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`age`	INT	NOT NULL,
	`gender`	INT	NOT NULL,
	`property`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`order_no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `trainer` (
	`trainer_no`	VARCHAR(50)	NOT NULL,
	`order_no`	INT	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`size`	VARCHAR(50)	NOT NULL,
	`age`	INT	NOT NULL,
	`currer`	VARCHAR(100)	NULL,
	`certificate`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`address`	VARCHAR(150)	NULL,
	`content`	VARCHAR(1000)	NULL,
	`user_id`	VARCHAR(40)	NOT NULL
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
	`user_num`	VARCHAR(100)	NOT NULL,
	`file_name`	VARCHAR(100)	NOT NULL,
	`file_flow`	VARCHAR(100)	NULL,
	`img_type`	VARCHAR(50)	NULL,
	`file_parent_no`	INT	NULL
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


INSERT INTO user_auth ( auth_no, user_id,  auth )
VALUES (2,'user2', 'ROLE_USER' );