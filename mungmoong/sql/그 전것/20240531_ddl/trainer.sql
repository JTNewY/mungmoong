-- Active: 1715069578448@@127.0.0.1@3306@mypet
TRUNCATE trainer;


 SELECT t.user_id
              ,t.name
              ,t.address
              ,t.mail
              ,t.phone
        FROM trainer t
        WHERE user_id = 'user'
        ;

        SELECT *
        FROM users;

select * from users;
update users set role = 0 where role = 1;  
update users set role = 2 where role = 1;  

truncate trainer;

-- Active: 1713528331467@@127.0.0.1@3306@mypet
TRUNCATE trainer;


 SELECT t.user_id
              ,t.name
              ,t.address
              ,t.mail
              ,t.phone
        FROM trainer t
        WHERE user_id = 'user'
        ;

        SELECT *
        FROM users;

select * from users;
update users set role = 0 where role = 1;  
update users set role = 2 where role = 1;  

truncate trainer;

-- 훈련사 테이블
DROP TABLE IF EXISTS trainer;
DROP TABLE trainer;
TRUNCATE TABLE trainer;

CREATE TABLE `trainer` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 훈련사 번호
    `user_id` VARCHAR(100) NOT NULL, -- 회원 아이디
    `name` VARCHAR(50) NOT NULL, -- 이름
    `gender` VARCHAR(50) NOT NULL, -- 성별
    `birth` VARCHAR(50) NOT NULL, -- 생일
    `mail` VARCHAR(50) NULL, -- 이메일
    `phone` VARCHAR(50) NULL, -- 핸드폰 번호
    `address` VARCHAR(150) NULL, -- 주소
    `reg_date` TIMESTAMP NULL, -- 등록일
    `upd_date` TIMESTAMP NULL, -- 수정일
    `content` TEXT NULL -- 소개
);
-- 경력 테이블
DROP TABLE IF EXISTS career;
TRUNCATE TABLE EXISTS career;
CREATE TABLE `career` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 경력 번호
    `user_id` VARCHAR(100) NOT NULL, -- 회원 아이디
    `trainer_no` INT NOT NULL, -- 훈련사 번호
    `name` VARCHAR(100) NULL, -- 경력 이름
    `reg_date` TIMESTAMP NULL, -- 등록일
    `upd_date` TIMESTAMP NULL, -- 수정일
    FOREIGN KEY (`trainer_no`) REFERENCES `trainer`(`no`) -- 외래 키 설정
);

-- 자격증 정보 테이블
DROP TABLE IF EXISTS certificate;
TRUNCATE TABLE EXISTS certificate;
CREATE TABLE `certificate` (
    `no` INT PRIMARY KEY AUTO_INCREMENT, -- 자격증 번호
    `user_id` VARCHAR(100) NOT NULL, -- 회원 아이디
    `trainer_no` INT NOT NULL, -- 훈련사 번호
    `name` VARCHAR(100) NULL, -- 자격증 이름
    `reg_date` TIMESTAMP NULL, -- 등록일
    `upd_date` TIMESTAMP NULL, -- 수정일
    FOREIGN KEY (`trainer_no`) REFERENCES `trainer`(`no`) -- 외래 키 설정
);
ALTER TABLE career
ADD CONSTRAINT fk_career_trainer_no FOREIGN KEY (trainer_no) REFERENCES trainer(no);

ALTER TABLE certificate
ADD CONSTRAINT fk_certificate_trainer_no FOREIGN KEY (trainer_no) REFERENCES trainer(no);

-- 스케줄 테이블
DROP TABLE IF EXISTS schedule;
TRUNCATE TABLE EXISTS schedule;
CREATE TABLE `schedule` (
   `no`         INT      NOT NULL   AUTO_INCREMENT PRIMARY KEY, -- 스케쥴 번호
   `trainer_no`   INT      NOT NULL, -- 훈련사 번호
   `title`   VARCHAR(50)         NULL, -- 이게 필요한지, 모달로 띄울건지 고민, 일단 보류 
   `content`   TEXT         NULL, -- //
   `date`   TIMESTAMP      NOT   NULL, -- 날짜
   `reg_date`   TIMESTAMP      NULL, -- 등록일
   `upd_date`   TIMESTAMP      NULL  -- 수정일
);

INSERT INTO `certificate` (user_id, trainer_no, name, reg_date, upd_date) VALUES
('t', 1, '반려견 훈련사 1급', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 2, '고양이 훈련사 1급', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 3, '반려동물 행동 교정사', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('t', 4, '펫 마사지 전문가', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user05', 5, '반려동물 상담사', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `career` (user_id, trainer_no, name, reg_date, upd_date) VALUES
('t', 1, '강아지 훈련 전문가', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 2, '고양이 행동 전문가', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user', 3, '반려견 행동 교정사', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('1', 4, '반려동물 상담 전문가', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('user05', 5, '펫 마사지 전문가', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `trainer` (user_id, name, gender, birth, mail, phone, address, reg_date, upd_date, content) VALUES
('t', '홍길동', 'Male', '1985-05-15', 'hong@example.com', '010-1234-5678', '서울시 강남구 역삼동 123-45', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '안녕하세요, 저는 홍길동입니다.'),
('user', '김영희', 'Female', '1990-07-20', 'kim@example.com', '010-8765-4321', '부산시 해운대구 우동 678-90', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '반갑습니다, 저는 김영희입니다.'),
('zz909', '박철수', 'Male', '1982-03-10', 'park@example.com', '010-1111-2222', '대구시 수성구 지산동 234-56', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '훈련사 박철수입니다.'),
('me', '이민정', 'Female', '1995-11-30', 'lee@example.com', '010-3333-4444', '인천시 남동구 구월동 789-01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '여러분을 도와드릴 이민정입니다.'),
('you', '최강민', 'Male', '1987-01-05', 'choi@example.com', '010-5555-6666', '광주시 서구 광천동 345-67', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '최강민입니다. 잘 부탁드립니다.');


INSERT INTO `users` (`user_id`, `password`, `name`, `birth`, `gender`, `address`, `mail`, `phone`, `reg_date`, `upd_date`, `role`, `enabled`) 
VALUES ('user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),1, 1);


INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 'user', 'ROLE_USER' );

-- 훈련사 회원

INSERT INTO `users` (`user_id`, `password`, `name`, `birth`, `gender`, `address`, `mail`, `phone`, `reg_date`, `upd_date`, `role`, `enabled`) 
VALUES ('t', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),2, 1);

INSERT INTO user_auth ( user_id,  auth ) VALUES ( 't', 'ROLE_USER' );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 't', 'ROLE_TRAINER' );


INSERT INTO career (user_id, trainer_no, name, reg_date, upd_date) VALUES 
('user', 6, 'Trainer', '2023-03-18 21:44:46', '2024-01-27 20:13:25'),
('user', 6, 'Advisor', '2021-08-28 08:33:39', '2023-04-05 14:44:46'),
('user', 6, 'Advisor', '2021-04-26 00:07:25', '2024-05-10 14:32:11'),
('user', 6, 'Consultant', '2021-08-19 03:47:57', '2023-02-23 05:25:48'),
('user', 6, 'Consultant', '2021-05-06 11:06:28', '2024-02-20 16:51:28'),
('user', 6, 'Advisor', '2023-06-01 21:28:07', '2024-05-29 17:15:44'),
('user', 6, 'Advisor', '2024-04-13 09:19:45', '2024-05-26 23:07:20'),
('user', 6, 'Coach', '2024-01-07 08:31:41', '2024-06-04 12:22:26'),
('user', 6, 'Consultant', '2024-01-18 02:47:09', '2024-04-09 23:47:34'),
('user', 6, 'Coach', '2020-11-13 21:05:50', '2022-05-14 02:08:57');
