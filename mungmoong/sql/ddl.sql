CREATE TABLE `manager` (
	`admin_id`	VARCHAR(40)	NOT NULL,
	`admin_password`	VARCHAR(40)	NOT NULL,
	`name`	VARCHAR(10)	NOT NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL
);

CREATE TABLE `pet` (
	`pet_no`	INT	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`age`	INT	NOT NULL,
	`gender`	INT	NOT NULL,
	`property`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`file`	VARCHAR(50)	NULL,
	`order_no`	INT	NOT NULL,
	`user_id`	VARCHAR(40)	NOT NULL
);

CREATE TABLE `trainer` (
	`trainer_id`	VARCHAR(50)	NOT NULL,
	`order_no`	INT	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`size`	VARCHAR(50)	NOT NULL,
	`age`	INT	NOT NULL,
	`currer`	INT	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`address`	VARCHAR(50)	NULL,
	`content`	VARCHAR(50)	NULL,
	`img`	VARCHAR(50)	NULL	COMMENT 'Candidate Key'
);

CREATE TABLE `reserve` (
	`date_no`	INT	NOT NULL,
	`date_time`	TIMESTAMP	NULL,
	`date_day`	TIMESTAMP	NULL,
	`reg_date`	VARCHAR(50)	NULL,
	`upd_date`	VARCHAR(50)	NULL
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
	`trainer_check`	INT	NULL,
	`reg_date`	VARCHAR(50)	NULL,
	`pay_check`	BOOLEAN	NULL,
	`date_no`	INT	NOT NULL,
	`user_id`	VARCHAR(40)	NOT NULL
);

CREATE TABLE `img_file` (
	`no`	INT	NULL,
	`user_num`	VARCHAR(50)	NOT NULL,
	`file_name`	INT	NOT NULL,
	`file_flow`	VARCHAR(50)	NULL,
	`img_type`	VARCHAR(50)	NULL,
	`file_parent_no`	INT	NULL
);

CREATE TABLE `users` (
	`user_id`	VARCHAR(40)	NOT NULL,
	`password`	VARCHAR(40)	NOT NULL,
	`name`	VARCHAR(10)	NOT NULL,
	`birth`	VARCHAR(20)	NOT NULL,
	`address`	VARCHAR(100)	NULL,
	`mail`	VARCHAR(30)	NULL,
	`phone`	INT	NULL,
	`adress`	VARCHAR(200)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL
);

CREATE TABLE `reply` (
	`file_no`	INT	NOT NULL,
	`board_no`	INT	NULL,
	`parent_no`	VARCHAR(45)	NULL,
	`writer`	VARCHAR(20)	NULL,
	`content`	INT	NULL,
	`upd_date`	TIMESTAMP	NOT NULL,
	`reg_date`	TIMESTAMP	NOT NULL,
	`board_no2`	INT	NOT NULL,
	`admin_id`	VARCHAR(40)	NOT NULL
);

CREATE TABLE `notice` (
	`notice_no`	INT	NOT NULL,
	`notice_content`	VARCHAR(50)	NOT NULL,
	`writer`	VARCHAR(50)	NOT NULL,
	`age`	TIMESTAMP	NOT NULL,
	`currer`	TIMESTAMP	NULL,
	`admin_id`	VARCHAR(40)	NOT NULL
);

CREATE TABLE `board` (
	`board_no`	INT	NOT NULL,
	`title`	VARCHAR(100)	NOT NULL,
	`content`	VARCHAR(1000)	NULL,
	`file`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NOT NULL,
	`upd_date`	TIMESTAMP	NULL,
	`user_id`	VARCHAR(40)	NOT NULL,
	`board_type`	INT	NULL
);

CREATE TABLE `review` (
	`review_no`	VARCHAR(50)	NOT NULL,
	`trainer_id`	VARCHAR(50)	NOT NULL,
	`user_id`	VARCHAR(40)	NOT NULL,
	`rating`	INT	NULL,
	`content`	VARCHAR(1000)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL
);

ALTER TABLE `manager` ADD CONSTRAINT `PK_MANAGER` PRIMARY KEY (
	`admin_id`
);

ALTER TABLE `pet` ADD CONSTRAINT `PK_PET` PRIMARY KEY (
	`pet_no`
);

ALTER TABLE `trainer` ADD CONSTRAINT `PK_TRAINER` PRIMARY KEY (
	`trainer_id`
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

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_id`
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

ALTER TABLE `pet` ADD CONSTRAINT `FK_PET_ORDERS` FOREIGN KEY (`order_no`) REFERENCES `orders`(`order_no`);
ALTER TABLE `pet` ADD CONSTRAINT `FK_PET_USERS` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);

ALTER TABLE `trainer` ADD CONSTRAINT `FK_TRAINER_ORDERS` FOREIGN KEY (`order_no`) REFERENCES `orders`(`order_no`);

ALTER TABLE `orders` ADD CONSTRAINT `FK_ORDERS_RESERVE` FOREIGN KEY (`date_no`) REFERENCES `reserve`(`date_no`);
ALTER TABLE `orders` ADD CONSTRAINT `FK_ORDERS_USERS` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);

ALTER TABLE `reply` ADD CONSTRAINT `FK_REPLY_BOARD` FOREIGN KEY (`board_no2`) REFERENCES `board`(`board_no`);
ALTER TABLE `reply` ADD CONSTRAINT `FK_REPLY_MANAGER` FOREIGN KEY (`admin_id`) REFERENCES `manager`(`admin_id`);

ALTER TABLE `notice` ADD CONSTRAINT `FK_NOTICE_MANAGER` FOREIGN KEY (`admin_id`) REFERENCES `manager`(`admin_id`);

ALTER TABLE `board` ADD CONSTRAINT `FK_BOARD_USERS` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);

ALTER TABLE `review` ADD CONSTRAINT `FK_REVIEW_TRAINER` FOREIGN KEY (`trainer_id`) REFERENCES `trainer`(`trainer_id`);
ALTER TABLE `review` ADD CONSTRAINT `FK_REVIEW_USERS` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`);