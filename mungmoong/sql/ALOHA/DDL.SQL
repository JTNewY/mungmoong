-- Active: 1705301251861@@127.0.0.1@3306@mypet

-- 펫테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS pet;
 

CREATE TABLE `pet` (
    `pet_no` INT PRIMARY KEY AUTO_INCREMENT,
    `petname` VARCHAR(50) NOT NULL,
    `age` INT NOT NULL,
    `petgender` INT NOT NULL,
    `character` VARCHAR(100) NULL,
    `type` VARCHAR(50) NULL,
   `special_notes` VARCHAR(255) NULL,
   `user_id` VARCHAR(100) NOT NULL,
    `reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);

-- 이미지파일테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS img_file;


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

-- 댓글테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS reply;


CREATE TABLE `reply` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NULL,
    `parent_no` INT NOT NULL,
    `parent_table` VARCHAR(100) NOT NULL,
    `writer` VARCHAR(100) NULL,
    `content` VARCHAR(1000) NULL,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- 공지사항테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS notice;


CREATE TABLE `notice` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `notice_content` VARCHAR(1000) NOT NULL,
    `writer` VARCHAR(50) NOT NULL,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 게시판테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS board;


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


-- 리뷰테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS review;


CREATE TABLE `review` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `rating` INT NULL,
    `content` VARCHAR(1000) NULL,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- 권한테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS user_auth;


CREATE TABLE `user_auth` (
    `auth_no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `auth` VARCHAR(40) NULL
);

-- 일정테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS schedule;


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


-- 경력테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS career;

CREATE TABLE `career` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 경력 번호
    `user_id` VARCHAR(100) NOT NULL, -- 회원 아이디
    `trainer_no` INT NOT NULL, -- 훈련사 번호
    `name` VARCHAR(100) NULL, -- 경력 이름
    `reg_date` TIMESTAMP NULL, -- 등록일
    `upd_date` TIMESTAMP NULL -- 수정일
);

-- 자격증테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS certificate;

CREATE TABLE `certificate` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 자격증 번호
    `user_id` VARCHAR(100) NOT NULL, -- 회원 아이디
    `trainer_no` INT NOT NULL, -- 훈련사 번호
    `name` VARCHAR(100) NULL, -- 자격증 이름
    `reg_date` TIMESTAMP NULL, -- 등록일
    `upd_date` TIMESTAMP NULL -- 수정일
);


-- 훈련사테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS trainer;


CREATE TABLE `trainer` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,
    `user_id` VARCHAR(100) NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `gender` VARCHAR(50) NOT NULL,
    `birth` VARCHAR(50) NOT NULL,
    `address` VARCHAR(150) NULL,
    `content` TEXT NULL,
    `phone` VARCHAR(50) NULL,
    `mail` VARCHAR(50) NULL,
    `reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
);


-- 회원테이블-------------------------------------------------------------------------------------

DROP TABLE IF EXISTS users;


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
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



-- 훈련상품테이블-------------------------------------------------------------------------------------
DROP TABLE IF EXISTS products;

CREATE TABLE `PRODUCTS` (
	`ID`	CHAR(255)	NOT NULL PRIMARY KEY,
    `TRAINER_NO` INT NOT NULL,                          -- 훈련사 번호
	`NAME`	VARCHAR(100)	NOT NULL,                   -- 훈련 교육 제목
	`DESCRIPTION`	VARCHAR(200)	NULL,               -- 훈련 간단 설명
	`CONTENT`	TEXT	NULL,                           -- 훈련 내용
	`PRICE`	INT	NOT NULL,                               -- 가격
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);
-- 카테고리테이블-------------------------------------------------------------------------------------
DROP TABLE IF EXISTS CATEGORIES;

CREATE TABLE `CATEGORIES` (
	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
	`CODE`	VARCHAR(100)	NOT NULL UNIQUE,
	`NAME`	VARCHAR(100)	NOT NULL,
	`SEQ`	INT	NOT NULL DEFAULT 1,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

-- 장바구니테이블-------------------------------------------------------------------------------------
DROP TABLE IF EXISTS CARTS;

CREATE TABLE `CARTS` (
	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
	`PRODUCTS_ID`	CHAR(36)	NOT NULL,
	`USER_ID`	CHAR(36)	NOT NULL,
	`AMOUNT`	INT	NOT NULL	DEFAULT 1,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);
-- 주문테이블 ------------------------------------------------------------------------------------

DROP TABLE IF EXISTS ORDERS;

CREATE TABLE `orders` (
	`NO` INT PRIMARY KEY AUTO_INCREMENT,                 -- 예약 번호
	`ID` CHAR(36) NOT NULL,                              -- 예약 id
	`PRODUCT_ID` VARCHAR(100) NOT NULL,                  -- 회원 id
	`USER_ID` VARCHAR(100) NOT NULL,                     -- 회원 id
	`pet_no` INT NOT NULL,                               -- 펫 번호
	`trainer_no` INT NOT NULL,                           -- 훈련사 번호
    `address` TEXT NOT NULL,                             -- 방문자 주소
	`resDate` TIMESTAMP NOT NULL,                        -- 예약날짜
	`MEMO` VARCHAR(255) NULL,                            -- 요청사항
	`TITLE` VARCHAR(255) NULL,                           -- 예약제목 - 상품명을 넣고
	`PRICE` INT NOT NULL DEFAULT 0,                      -- 예약금액
	`STATUS` VARCHAR(20) NOT NULL DEFAULT 'pending' COMMENT '결제대기, 결제완료, 환불, 승인' ,
	`reg_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `upd_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `meaning` INT NOT NULL DEFAULT 0                      -- 훈련 진행 상황 
);

-- 주문상세테이블-------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS ORDER_ITEMS;

-- CREATE TABLE `ORDER_ITEMS` (
-- 	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
-- 	`DATE_NO` INT NOT NULL,            -- 예약 번호
-- 	`PRODUCTS_ID`	CHAR(36)	NOT NULL,
-- 	`PRICE`	INT	NOT NULL	DEFAULT 0,
-- 	`AMOUNT`	INT	NULL,
-- 	`resDate` TIMESTAMP NOT NULL,                        -- 예약날짜
-- 	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
-- 	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
-- );
-- 결제테이블------------------------------------------------------------------------------------
DROP TABLE IF EXISTS PAYMENTS;

CREATE TABLE `PAYMENTS` (
	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
	`ORDER_NO` INT NOT NULL,            -- 예약 번호
	`PAYMENT_METHOD`	VARCHAR(255)	NULL,
	`STATUS`	VARCHAR(255)	NULL,
	`PAID_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

-- 취소테이블 --------------------------------------------------------------------------------
DROP TABLE IF EXISTS CANCELLATIONS;

CREATE TABLE `CANCELLATIONS` (
	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
	`DATE_NO` INT NOT NULL,            -- 예약 번호
	`TYPE`	ENUM('CANCEL','EXCHANGE')	NOT NULL	DEFAULT 'CANCEL'	COMMENT '주문취소,환불',
	`STATUS`	ENUM('PENDING','complete')	NULL	DEFAULT 'PENDING'	COMMENT '취소요청(대기), 처리완료',
	`REASON`	TEXT	NULL,
	`REFUNDED_AMOUNT`	INT	NOT NULL	DEFAULT 0,
	`IS_CONFIRMED`	TINYINT(1)	NOT NULL	DEFAULT 0	COMMENT '0: 미승인, 1: 승인',
	`IS_REFUND`	TINYINT(1)	NOT NULL	DEFAULT 0	COMMENT '0: 대기, 1: 처리완료',
	`ACCOUNT_NUMBER`	VARCHAR(100)	NULL,
	`BANK_NAME`	VARCHAR(100)	NULL,
	`DEPOSITOR`	VARCHAR(100)	NULL,
	`CANCELED_AT`	TIMESTAMP	NULL,
	`COMPLETED_AT`	TIMESTAMP	NULL,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);
-- 주문파일테이블 ---------------------------------------------------------------------------------------
DROP TABLE IF EXISTS FILES;

CREATE TABLE `FILES` (
	`ID`	CHAR(36)	NOT NULL PRIMARY KEY,
	`PARENT_TABLE`	VARCHAR(100)	NOT NULL,
	`PARENT_ID`	VARCHAR(100)	NOT NULL,
	`NAME`	TEXT	NOT NULL,
	`ORIGIN_NAME`	TEXT	NOT NULL,
	`PATH`	TEXT	NOT NULL,
	`SIZE`	BIGINT	NOT NULL	DEFAULT 0,
	`IS_MAIN`	BOOLEAN	NOT NULL	DEFAULT FALSE,
	`SEQ`	INT	NOT NULL	DEFAULT 0,
	`CREATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`UPDATED_AT`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

-- 주소테이블 ---------------------------------------------------------------------------------

DROP TABLE IF EXISTS ADDRESS;

CREATE TABLE `ADDRESS` (
    `NO` INT NOT NULL PRIMARY KEY,
    `USER_NO` INT NOT NULL,
    `TITLE` VARCHAR(100) NULL COMMENT '집,회사,사무실',
    `RECIPIENT` VARCHAR(100) NULL,
    `ADDRESS` TEXT NULL,
    `ADDRESS_DETAIL` TEXT NULL,
    `PHONE` VARCHAR(100) NULL,
    `REQUEST` TEXT NULL COMMENT '요청사항',
    `IS_DEFAULT` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '1: 기본주소지, 0: 기타',
    `ACCESS_NO` VARCHAR(100) NULL COMMENT '공동현관 출입번호',
    `CREATED_AT` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_AT` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 주문상태테이블 --------------------------------------------------------------------------------
DROP TABLE IF EXISTS `SHIPMENTS`;

CREATE TABLE `SHIPMENTS` (
    `NO` INT NOT NULL,
    `ORDERS_NO` INT NOT NULL,
    `ADDRESS_NO` CHAR(36) NOT NULL,
    `TRACKING_NO` VARCHAR(255) NULL,
    `SHIP_COMPANY` VARCHAR(255) NULL,
    `STATUS` ENUM(
        'PENDING',
        'START',
        'SHIPPING',
        'DELIVERED',
        'CANCELLED'
    ) NULL COMMENT '배송준비중, 배송시작, 배송중, 배송완료, 주문취소',
    `CREATED_AT` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_AT` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- QnA 테이블 --------------------------------------------------------------------------------

DROP TABLE IF EXISTS `QnA`;
CREATE TABLE `QnA` (
	`no`	INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`title`	VARCHAR(100)	NOT NULL,
	`writer`	VARCHAR(100)	NOT NULL,
	`content`	VARCHAR(100)	NULL,
	`board_type`	VARCHAR(100)	NOT NULL,
	`reg_date`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`upd_date`	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


--- 소셜 로그인 ---------------------------------------------------------------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
	`auth_no` 	INT 	NOT NULL PRIMARY KEY,
	`user_id`	VARCHAR(100)	NOT NULL,
	`auth`	VARCHAR(40)	NULL
);

DROP TABLE IF EXISTS `user_social`;
CREATE TABLE user_social (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    social_platform VARCHAR(255) NOT NULL,
    social_id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    mail VARCHAR(255),
    picture VARCHAR(255),
    linked_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



-- END