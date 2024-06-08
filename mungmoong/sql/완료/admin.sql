-- Active: 1713353106333@@127.0.0.1@3306@mypet
INSERT INTO `board` (`title`, `content`, `file_name`, `reg_date`, `user_id`, `board_type`) VALUES
('첫 번째 게시물', '첫 번째 게시물의 내용입니다.', 'file1.jpg', NOW(), 'user1', 1),
('두 번째 게시물', '두 번째 게시물의 내용입니다.', 'file2.jpg', NOW(), 'user2', 2),
('세 번째 게시물', '세 번째 게시물의 내용입니다.', NULL, NOW(), 'user3', 1),
('네 번째 게시물', '네 번째 게시물의 내용입니다.', 'file3.jpg', NOW(), 'user1', 3),
('다섯 번째 게시물', '다섯 번째 게시물의 내용입니다.', NULL, NOW(), 'user2', 2);


INSERT INTO `board` (`title`, `content`, `file_name`, `reg_date`, `user_id`, `board_type`)
SELECT (`title`, `content`, `file_name`, `reg_date`, `user_id`, `board_type`)
FROM board;