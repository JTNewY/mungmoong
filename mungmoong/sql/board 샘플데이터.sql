drop table board;
CREATE TABLE `board` (
    `board_no` INT NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `content` VARCHAR(1000) NULL,
    `file_name` VARCHAR(100) NULL,
    `reg_date` TIMESTAMP NOT NULL,
    `upd_date` TIMESTAMP NULL,
    `user_id` VARCHAR(100) NOT NULL,
    `board_type` INT NULL,
    PRIMARY KEY (`board_no`)
);
alter Table board MODIFY board_no INT AUTO_INCREMENT;
alter Table board MODIFY upd_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP;
alter Table board MODIFY reg_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP;

INSERT INTO `board` (`board_no`, `title`, `content`, `file_name`, `reg_date`, `upd_date`, `user_id`, `board_type`) VALUES
(1, 'First Post', 'This is the content of the first post.', 'file1.jpg', '2024-05-31 10:00:00', '2024-05-31 12:00:00', 'user1', 1),
(2, 'Second Post', 'This is the content of the second post.', NULL, '2024-05-31 11:00:00', NULL, 'user2', 2),
(3, 'Third Post', 'This is the content of the third post.', 'file3.pdf', '2024-05-31 12:00:00', '2024-05-31 13:00:00', 'user3', 1),
(4, 'Fourth Post', 'This is the content of the fourth post.', NULL, '2024-05-31 13:00:00', NULL, 'user4', 2);