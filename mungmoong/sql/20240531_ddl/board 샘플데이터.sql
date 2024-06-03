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
alter Table board MODIFY upd_date TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP;
alter Table board MODIFY reg_date TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP;

INSERT INTO `board` (`board_no`, `title`, `content`, `file_name`, `reg_date`, `upd_date`, `user_id`, `board_type`) VALUES
(1, '첫 번째 게시물', '이것은 첫 번째 게시물의 내용입니다.', 'file1.txt', '2024-05-31 12:00:00', '2024-05-31 12:00:00', 'user1', 1),
(2, '두 번째 게시물', '이것은 두 번째 게시물의 내용입니다.', 'file2.txt', '2024-05-31 13:00:00', '2024-05-31 13:00:00', 'user2', 2),
(3, '세 번째 게시물', '이것은 세 번째 게시물의 내용입니다.', 'file3.txt', '2024-05-31 14:00:00', '2024-05-31 14:00:00', 'user3', 1),
(4, '네 번째 게시물', '이것은 네 번째 게시물의 내용입니다.', 'file4.txt', '2024-05-31 15:00:00', '2024-05-31 15:00:00', 'user1', 2),
(5, '다섯 번째 게시물', '이것은 다섯 번째 게시물의 내용입니다.', 'file5.txt', '2024-05-31 16:00:00', '2024-05-31 16:00:00', 'user2', 1);