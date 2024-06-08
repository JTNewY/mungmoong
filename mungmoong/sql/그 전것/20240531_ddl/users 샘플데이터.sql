-- Active: 1717746144890@@127.0.0.1@3306@mypet

-- user_id, password, name, birth, address, mail,phone, role
-- #{user_id}, #{password}, #{name}, #{birth}, #{address}, #{mail}, #{phone}, #{role}

TRUNCATE users;
TRUNCATE user_auth;

drop table users;

-- 일반 회원s
=======
DROP TABLE users;
TRUNCATE TABLE EXISTS  users;
-- 회원 테이블
TRUNCATE TABLE users;
CREATE TABLE `users` (
   `user_id`   VARCHAR(100)   NOT NULL,
   `password`   VARCHAR(100)   NOT NULL,
   `name`   VARCHAR(50)   NOT NULL,
   `birth`   TIMESTAMP   NOT NULL,
   `gender`      VARCHAR(50)   NOT NULL,  
   `address`   VARCHAR(150)   NULL,
   `mail`   VARCHAR(50)   NULL,
   `phone`   VARCHAR(50)   NULL,
   `reg_date`   TIMESTAMP   NULL,
   `upd_date`   TIMESTAMP   NULL,
   `role`   INT   NULL,
   `enabled` INT NULL
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_id`
);

INSERT INTO `users` (`user_id`, `password`, `name`, `birth`, `gender`, `address`, `mail`, `phone`, `reg_date`, `upd_date`, `role`, `enabled`) 
VALUES ('usera', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userb', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userc', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userd', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('usere', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userf', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userg', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userh', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('useri', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userj', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userk', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userl', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userm', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('usern', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('usero', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1)
      ,('userp', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101, 'Male', '인주대로 1000번길','user@mail.com' , '01012341234',now(),now(),0, 1);


INSERT INTO `users` (`user_id`, `password`, `name`, `birth`, `gender`, `address`, `mail`, `phone`, `reg_date`, `upd_date`, `role`, `enabled`)
SELECT `user_id`, `password`, `name`, `birth`, `gender`, `address`, `mail`, `phone`, `reg_date`, `upd_date`, `role`, `enabled`
FROM users; 



INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 'user', 'ROLE_USER' );

-- 훈련사 회원
INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 't', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '오은아 훈련사', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 't', 'ROLE_USER' );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 't', 'ROLE_TRAINER' );