-- Active: 1714104693276@@127.0.0.1@3306@mypet
DROP TABLE users;
DROP TABLE trainer;

SELECT * FROM users;

INSERT INTO users ( user_id, password, name, birth, address, mail, gender, phone, role, enabled)
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', now(),'인주대로 1000번길','user@mail.com', '남자', '01012341234','0' ,1);


INSERT INTO user_auth ( auth_no, user_id,  auth )
VALUES (1,'user', 'ROLE_USER' );

SELECT * FROM trainer;


