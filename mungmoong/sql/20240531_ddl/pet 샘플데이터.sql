-- Active: 1716798076500@@127.0.0.1@3306@mypet


DROP TABLE IF EXISTS pet;

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

INSERT INTO `pet` (`pet_no`, `petname`, `pettype`, `age`, `petgender`, `character`, `reg_date`, `upd_date`, `order_no`, `user_id`) VALUES
(1, 'Buddy', 'Dog', 3, 1, 'Friendly', '2024-06-07 12:00:00', '2024-06-07 12:00:00', 1234, 'user123'),
(2, 'Whiskers', 'Cat', 2, 2, 'Playful', '2024-06-07 12:00:00', '2024-06-07 12:00:00', 5678, 'user456'),
(3, 'Rocky', 'Dog', 4, 1, 'Loyal', '2024-06-07 12:00:00', '2024-06-07 12:00:00', 91011, 'user789'),
(4, 'Fluffy', 'Rabbit', 1, 2, 'Curious', '2024-06-07 12:00:00', '2024-06-07 12:00:00', 121314, 'user101112');
