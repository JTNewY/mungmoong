SELECT * FROM mypet.pet;

-- schema.sql 파일 내용
DROP TABLE IF EXISTS pet;

CREATE TABLE `pet` (
    `pet_no` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(100) NOT NULL,
    `petname` VARCHAR(50) NOT NULL,
    `pettype` VARCHAR(50) NOT NULL,
    `age` INT NOT NULL,
    `petgender` INT NOT NULL,
    `character` VARCHAR(100) NULL,
    `special_notes` VARCHAR(255) NULL,
    `reg_date` TIMESTAMP NULL,
    `upd_date` TIMESTAMP NULL,
    `order_no` INT NOT NULL
);




-- 샘플 데이터 삽입
INSERT INTO `pet` (`petname`, `pettype`, `age`, `petgender`, `character`, `reg_date`, `upd_date`, `order_no`, `user_id`, `special_notes`) VALUES
('Buddy', 'Dog', 3, 1, 'Friendly', NOW(), NOW(), 101, 'user1', 'Loves to play fetch'),
('Mittens', 'Cat', 2, 2, 'Curious', NOW(), NOW(), 102, 'user2', 'Prefers to stay indoors');


