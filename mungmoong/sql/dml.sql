DROP TABLE users;
DROP TABLE trainer;

SELECT * FROM users;

INSERT INTO users ( user_id, password, name, birth, address, mail, gender, phone, role, enabled)
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', now(),'인주대로 1000번길','user@mail.com', '남자', '01012341234','0' ,1);


INSERT INTO user_auth ( auth_no, user_id,  auth )
VALUES (1,'user', 'ROLE_USER' );

SELECT * FROM trainer;

INSERT INTO `trainer` (`no`, `name`, `gender`, `birth`, `address`, `reg_date`, `upd_date`, `content`, `user_id`, `mail`, `phone`)
VALUES
(001, '이태원', '남성', '1988-01-01', '서울특별시 강남구 테헤란로 123', now(), NULL, '긍정적 강화 중심의 개 훈련에 열정적입니다.', 'user123', 'itaewon@example.com', '010-1234-5678'),
(002, '오은아', '여성', '1995-01-01', '부산광역시 해운대구 해운대로 456', now(), NULL, '행동 수정 및 기민 훈련 전문.', 'user456', 'oeuna@example.com', '010-2345-6789'),
(003, '권혁준', '남성', '1983-01-01', '인천광역시 남동구 인주대로 789', now(), NULL, '개 피트니스 및 재활 전문가.', 'user789', 'khj@example.com', '010-3456-7890'),
(004, '김재희', '여성', '1991-01-01', '대구광역시 수성구 달구벌대로 321', now(), NULL, '복종 훈련 및 행동 교정 집중.', 'user101', 'jaehee@example.com', '010-4567-8901'),
(005, '정태진', '남성', '1994-01-01', '대전광역시 서구 둔산로 654', now(), NULL, '장애인을 위한 서비스 도그 훈련에 전념.', 'user202', 'taejin@example.com', '010-5678-9012');

