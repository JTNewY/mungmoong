-- Active: 1714104693276@@127.0.0.1@3306@mypet

-- user_id, password, name, birth, address, mail,phone, role
-- #{user_id}, #{password}, #{name}, #{birth}, #{address}, #{mail}, #{phone}, #{role}

TRUNCATE users;
TRUNCATE user_auth;



-- 일반 회원
=======
CREATE TABLE `users` (
   `user_id`   VARCHAR(100)   NOT NULL,
   `password`   VARCHAR(100)   NOT NULL,
   `name`   VARCHAR(50)   NOT NULL,
   `birth`   TIMESTAMP   NOT NULL,
   `gender`      VARCHAR(50)   NOT NULL,   -- 성별
   `address`   VARCHAR(150)   NULL,
   `mail`   VARCHAR(50)   NULL,
   `gender`   VARCHAR(50)   NOT NULL,
   `phone`   VARCHAR(50)   NULL,
   `reg_date`   TIMESTAMP   NULL,
   `upd_date`   TIMESTAMP   NULL,
   `role`   INT   NULL,
   `enabled` INT NULL
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (
	`user_id`
);


INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 'user', 'ROLE_USER' );

-- 훈련사 회원
INSERT INTO users ( user_id, password, name, birth, address, mail,phone, role,reg_date,upd_date,enabled )
VALUES ( 't', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '오은아 훈련사', 20000101,'인주대로 1000번길','user@mail.com' , '01012341234','0',now(),now(),1 );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 't', 'ROLE_USER' );
INSERT INTO user_auth ( user_id,  auth ) VALUES ( 't', 'ROLE_TRAINER' );