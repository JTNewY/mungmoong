DROP TABLE trainer;
DROP TABLE career;
DROP TABLE certificate;


SELECT * FROM trainer;
SELECT * FROM career;
SELECT * FROM certificate;


-- 훈련사 테이블
DROP TABLE IF EXISTS trainer;
DROP TABLE trainer;
TRUNCATE TABLE trainer;

CREATE TABLE `trainer` (
    `no` INT PRIMARY KEY AUTO_INCREMENT,-- 훈련사 번호
    `user_id` VARCHAR(100) NOT NULL,    -- 회원 아이디
    `name` VARCHAR(50) NOT NULL,        -- 이름
    `gender` VARCHAR(50) NOT NULL,      -- 성별
    `birth` VARCHAR(50) NOT NULL,       -- 생일
    `mail` VARCHAR(50) NULL,            -- 이메일
    `phone` VARCHAR(50) NULL,           -- 핸드폰 번호
    `address` VARCHAR(150) NULL,        -- 주소
    `reg_date` TIMESTAMP NULL,          -- 등록일
    `upd_date` TIMESTAMP NULL,          -- 수정일
    `content` TEXT NULL                 -- 소개
);
-- 경력 테이블
TRUNCATE TABLE EXISTS career;
CREATE TABLE `career` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 경력 번호
    `user_id` VARCHAR(100) NOT NULL,     -- 회원 아이디
    `trainer_no` INT NOT NULL,           -- 훈련사 번호
    `name` VARCHAR(100) NULL,            -- 경력 이름
    `reg_date` TIMESTAMP NULL,           -- 등록일
    `upd_date` TIMESTAMP NULL,           -- 수정일
    FOREIGN KEY (`trainer_no`) REFERENCES `trainer`(`no`) -- 외래 키 설정
);

-- 자격증 테이블
TRUNCATE TABLE EXISTS certificate;
CREATE TABLE `certificate` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 자격증 번호
    `user_id` VARCHAR(100) NOT NULL,     -- 회원 아이디
    `trainer_no` INT NOT NULL,           -- 훈련사 번호
    `name` VARCHAR(100) NULL,            -- 자격증 이름
    `reg_date` TIMESTAMP NULL,           -- 등록일
    `upd_date` TIMESTAMP NULL,           -- 수정일
    FOREIGN KEY (`trainer_no`) REFERENCES `trainer`(`no`) -- 외래 키 설정
);



-- 스케줄 테이블
DROP TABLE IF EXISTS schedule;
TRUNCATE TABLE EXISTS schedule;
CREATE TABLE `schedule` (
	`no`    INT PRIMARY KEY AUTO_INCREMENT, -- 스케쥴 번호
	`trainer_no`	INT		NOT NULL,       -- 훈련사 번호
	`title`	VARCHAR(50)			NULL,       -- 이게 필요한지, 모달로 띄울건지 고민, 일단 보류 
	`content`	TEXT			NULL,       -- //
	`date`	TIMESTAMP		NOT	NULL,       -- 날짜
	`reg_date`	TIMESTAMP		NULL,       -- 등록일
	`upd_date`	TIMESTAMP		NULL        -- 수정일
);

-----------------------------------------------------------------------------------------

select * from users;
update users set role = 0 where role = 1;  
update users set role = 2 where role = 1;  

truncate trainer;

-- 기본 필요한 데이터
INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 'user', 'ROLE_USER' );