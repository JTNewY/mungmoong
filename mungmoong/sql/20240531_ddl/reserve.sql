

TRUNCATE trainer;
INSERT INTO `trainer` (`order_no`, `name`, `gender`, `birth`, `mail`, `phone`, `address`, `career`, `certificate`, `content`, `user_id`) 
VALUES (1001, '김영훈', '남성', '1990-05-15', 'kim.younghoon@example.com', '010-1234-5678', '서울특별시 강남구', '프로 트레이너로 5년 경력 보유', '헬스 트레이너 자격증 소지', '운동 및 영양 전문가입니다.', 'user');


-- 예약 샘플 데이터
INSERT INTO reserve(user_id, trainer_no, pet_no, date, order_no, request, reg_date)
VALUES ('user', 1, 1, now(), 1, '잘부탁드립니다', now());