DROP TABLE users;
DROP TABLE trainer;

SELECT * FROM users;

INSERT INTO users ( user_id, password, name, birth, address, mail, gender, phone, role, enabled)
VALUES ( 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', now(),'인주대로 1000번길','user@mail.com', '남자', '01012341234','0' ,1);


INSERT INTO user_auth ( auth_no, user_id,  auth )
VALUES (1,'user', 'ROLE_USER' );

SELECT * FROM trainer;

INSERT INTO `trainer` (`no`, `order_no`, `name`, `gender`, `birth`, `address`, `reg_date`, `upd_date`, `career`, `certificate`, `content`, `user_id`, `mail`, `phone`)
VALUES
(001, 1001, '이태원', '남성', '1988-01-01', '서울특별시 강남구 테헤란로 123', '2023-01-15 10:00:00', '2023-05-20 14:30:00', '5년 경력', '공인 전문 도그 트레이너', '긍정적 강화 중심의 개 훈련에 열정적입니다.', 'user123', 'itaewon@example.com', '010-1234-5678'),
(002, 1002, '오은아', '여성', '1995-01-01', '부산광역시 해운대구 해운대로 456', '2023-02-18 11:00:00', '2023-06-25 16:45:00', '3년 경력', '공인 도그 행동 컨설턴트', '행동 수정 및 기민 훈련 전문.', 'user456', 'oeuna@example.com', '010-2345-6789'),
(003, 1003, '권혁준', '남성', '1983-01-01', '인천광역시 남동구 인주대로 789', '2023-03-22 09:00:00', '2023-07-15 09:15:00', '10년 경력', '공인 개 피트니스 트레이너', '개 피트니스 및 재활 전문가.', 'user789', 'khj@example.com', '010-3456-7890'),
(004, 1004, '김재희', '여성', '1991-01-01', '대구광역시 수성구 달구벌대로 321', '2023-04-10 14:00:00', '2023-08-05 11:00:00', '7년 경력', '공인 도그 트레이너 및 행동 전문가', '복종 훈련 및 행동 교정 집중.', 'user101', 'jaehee@example.com', '010-4567-8901'),
(005, 1005, '정태진', '남성', '1994-01-01', '대전광역시 서구 둔산로 654', '2023-05-05 13:00:00', '2023-09-10 15:30:00', '4년 경력', '공인 서비스 도그 트레이너', '장애인을 위한 서비스 도그 훈련에 전념.', 'user202', 'taejin@example.com', '010-5678-9012');
