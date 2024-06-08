-- 펫테이블-------------------------------------------------------------------------------------
select * from pet;

DROP TABLE IF EXISTS pet;

TRUNCATE TABLE pet;

CREATE TABLE `pet` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `file_no` INT NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `age` INT NOT NULL,
    `petgender` INT NOT NULL,
    `character` VARCHAR(100) NULL,
    `type` VARCHAR(50) NULL,
     `user_id` VARCHAR(100) NOT NULL,
    `reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `pet`
ADD FOREIGN KEY (`file_no`) REFERENCES `img_file` (`file_no`);

-- 이미지파일테이블-------------------------------------------------------------------------------------
select * from img_file;

DROP TABLE IF EXISTS img_file;

TRUNCATE TABLE img_file;

CREATE TABLE `img_file` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `parent_no` INT NOT NULL,
    `parent_table` VARCHAR(100) NOT NULL,
    `file_name` VARCHAR(100) NOT NULL,
    `file_path` VARCHAR(100) NOT NULL,
    `file_size` LONG NULL,
    `file_code` VARCHAR(50) NULL,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `img_file`
ADD CONSTRAINT `PK_IMG_FILE` PRIMARY KEY (`no`);
-- 댓글테이블-------------------------------------------------------------------------------------
select * from reply;

DROP TABLE IF EXISTS reply;

TRUNCATE TABLE reply;

CREATE TABLE `reply` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `board_no2` INT NOT NULL,
    `user_id` VARCHAR(100) NOT NULL,
    `parent_no` VARCHAR(100) NULL,
    `writer` VARCHAR(100) NULL,
    `content` VARCHAR(1000) NULL,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `reply`
ADD CONSTRAINT `PK_REPLY` PRIMARY KEY (`no`);
-- 공지사항테이블-------------------------------------------------------------------------------------
select * from notice;

DROP TABLE IF EXISTS notice;

TRUNCATE TABLE notice;

CREATE TABLE `notice` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `notice_content` VARCHAR(1000) NOT NULL,
    `writer` VARCHAR(50) NOT NULL,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `notice`
ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (`notice_no`);
-- 게시판테이블-------------------------------------------------------------------------------------
select * from board;

DROP TABLE IF EXISTS board;

TRUNCATE TABLE board;

CREATE TABLE `board` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `writer` VARCHAR(100) NULL,
    `content` VARCHAR(1000) NULL,
    `file_no` VARCHAR(100) NULL,
    `board_type` INT NULL, -- 1.일반 2관리자3.공지사항(예시)
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `board`
ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (`no`);

-- 리뷰테이블-------------------------------------------------------------------------------------
select * from review;

DROP TABLE IF EXISTS review;

TRUNCATE TABLE review;

CREATE TABLE `review` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `rating` INT NULL,
    `content` VARCHAR(1000) NULL,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `review`
ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (`review_no`);

-- 권한테이블-------------------------------------------------------------------------------------
select * from user_auth;

DROP TABLE IF EXISTS user_auth;

TRUNCATE TABLE user_auth;

CREATE TABLE `user_auth` (
    `auth_no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `auth` VARCHAR(40) NULL
);

ALTER TABLE `user_auth`
ADD CONSTRAINT `PK_USER_AUTH` PRIMARY KEY (`auth_no`);
-- 일정테이블-------------------------------------------------------------------------------------
select * from schedule;

DROP TABLE IF EXISTS schedule;

TRUNCATE TABLE schedule;

CREATE TABLE `schedule` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `trainer_no` INT NOT NULL,
    `title` VARCHAR(50) NULL, --  휴무
    `content` TEXT NULL,
    `schedule_date` TIMESTAMP NULL,
    `reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `schedule`
ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (`no`);

-- 경력테이블-------------------------------------------------------------------------------------
select * from career;

DROP TABLE IF EXISTS career;

TRUNCATE TABLE career;
CREATE TABLE `career` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 경력 번호
    `user_id` VARCHAR(100) NOT NULL, -- 회원 아이디
    `trainer_no` INT NOT NULL, -- 훈련사 번호
    `name` VARCHAR(100) NULL, -- 경력 이름
    `reg_date` TIMESTAMP NULL, -- 등록일
    `upd_date` TIMESTAMP NULL, -- 수정일
    FOREIGN KEY (`trainer_no`) REFERENCES `trainer`(`no`) -- 외래 키 설정
);

ALTER TABLE `career`
ADD CONSTRAINT `PK_CAREER` PRIMARY KEY (`no`);
-- 자격증테이블-------------------------------------------------------------------------------------
select * from certificate;

DROP TABLE IF EXISTS certificate;

TRUNCATE TABLE certificate;

CREATE TABLE `certificate` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `name` VARCHAR(100) NULL,
    `reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `certificate`
ADD CONSTRAINT `PK_CERTIFICATE` PRIMARY KEY (`no`);

-- 훈련사테이블-------------------------------------------------------------------------------------
select * from trainer;

DROP TABLE IF EXISTS trainer;

TRUNCATE TABLE trainer;

CREATE TABLE `trainer` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `gender` VARCHAR(50) NOT NULL,
    `birth` VARCHAR(50) NOT NULL,
    `address` VARCHAR(150) NULL,
    `content` TEXT NULL,
    `phone` INT NULL,
    `mail` VARCHAR(50) NULL,
    `reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `trainer`
ADD CONSTRAINT `PK_TRAINER` PRIMARY KEY (`no`);

-- 회원테이블-------------------------------------------------------------------------------------
select * from users;

DROP TABLE IF EXISTS users;

TRUNCATE TABLE users;

CREATE TABLE `users` (
    `user_id` VARCHAR(100) NOT NULL PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `birth` VARCHAR(50) NULL,
    `address` VARCHAR(150) NULL,
    `mail` VARCHAR(50) NULL,
    `phone` VARCHAR(50) NULL,
    `gender` VARCHAR(50) NULL,
    `enabled` INT NULL,
    `role` INT NULL,
    `reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `users`
ADD CONSTRAINT `PK_USERS` PRIMARY KEY (`user_id`);
-- 샘플 데이터 삽입
INSERT INTO
    `users` (
        `user_id`,
        `password`,
        `name`,
        `birth`,
        `gender`,
        `address`,
        `mail`,
        `phone`,
        `reg_date`,
        `upd_date`,
        `role`,
        `enabled`
    )
VALUES (
        'user2',
        '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92',
        'Alice',
        '1980-01-01 00:00:00',
        'Female',
        '123 Apple St, Wonderland',
        'alice@example.com',
        '123-456-7890',
        '2023-01-01 12:00:00',
        '2024-01-01 12:00:00',
        1,
        1
    );


-- END (예약 결제관련테이블은 java/board에 있습니다 추후 ddl 추가하겠습니다 20240608 재희)