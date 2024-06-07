
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


INSERT INTO `pet` (`user_id`, `petname`, `pettype`, `age`, `petgender`, `character`, `special_notes`, `reg_date`, `upd_date`, `order_no`) VALUES
('ttt', 'Buddy', 'Dog', 3, 1, 'Friendly', 'Loves playing fetch.', NOW(), NOW(), 1234),
('user', 'Whiskers', 'Cat', 2, 2, 'Playful', 'Enjoys chasing lasers.', NOW(), NOW(), 5678),
('t', 'Rocky', 'Dog', 4, 1, 'Loyal', 'Excellent guard dog.', NOW(), NOW(), 91011),
('user101112', 'Fluffy', 'Rabbit', 1, 2, 'Curious', 'Likes to explore new places.', NOW(), NOW(), 121314);
