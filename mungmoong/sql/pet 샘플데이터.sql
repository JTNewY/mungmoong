



CREATE TABLE `pet` (
	`pet_no`	INT	NOT NULL,
	`petname`	VARCHAR(50)	NOT NULL,
	`pettype` VARCHAR(50)	NOT NULL,
	`age`	INT	NOT NULL,
	`gender`	INT	NOT NULL,
	`character`	VARCHAR(100)	NULL,
	`reg_date`	TIMESTAMP	NULL,
	`upd_date`	TIMESTAMP	NULL,
	`order_no`	INT	NOT NULL,
	`user_id`	VARCHAR(100)	NOT NULL
);

ALTER TABLE `pet` MODIFY COLUMN `pet_no` INT AUTO_INCREMENT PRIMARY KEY;