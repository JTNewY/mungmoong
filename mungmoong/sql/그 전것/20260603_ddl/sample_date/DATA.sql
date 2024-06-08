-- Active: 1716798076500@@127.0.0.1@3306@aloha

TRUNCATE user;
-- 사용자
INSERT INTO user ( `ID`, `USERNAME`, `PASSWORD`, `NAME`, `PHONE` )
VALUES ( 'u1b2c3d4-e5f6-7890-abcd-ef1234567890', 'user', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '사용자', '010-1234-1234');

-- 관리자
INSERT INTO user ( `ID`,  `USERNAME`, `PASSWORD`, `NAME`, `PHONE` )
VALUES ( 'u1b2c3d4-e5f6-7890-abcd-aa1234567890', 'admin', '$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '관리자', '010-0000-0000' );


TRUNCATE user_auth;
-- 권한
-- 사용자 
-- * 권한 : ROLE_USER
INSERT INTO user_auth ( `ID`, `USERNAME`,  `auth` )
VALUES ( UUID(), 'user', 'ROLE_USER' );

-- 관리자
-- * 권한 : ROLE_USER, ROLE_ADMIN
INSERT INTO user_auth ( `ID`, `USERNAME`,  `auth` )
VALUES ( UUID(), 'admin', 'ROLE_USER' );

INSERT INTO user_auth ( `ID`, `USERNAME`,  `auth` )
VALUES ( UUID(), 'admin', 'ROLE_ADMIN' );

--------------------------------------------------------------------------------------------------
TRUNCATE products;
-- Inserting sample data
INSERT INTO `PRODUCTS` (`ID`, `NAME`, `CATEGORY`, `DESCRIPTION`, `CONTENT`, `PRICE`, `STOCK`, `CREATED_AT`, `UPDATED_AT`) 
VALUES
('5e6f7g8h-9012-34ab-cdef-5678901234ef', '상의 상품 1', 'TOP', '상의 상품 1 설명', '상의 상품 1의 내용', 10000, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('6f7g8h9i-0123-45ab-cdef-6789012345fg', '상의 상품 2', 'TOP', '상의 상품 2 설명', '상의 상품 2의 내용', 15000, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('7g8h9i0j-1234-56ab-cdef-7890123456gh', '상의 상품 3', 'TOP', '상의 상품 3 설명', '상의 상품 3의 내용', 20000, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('8h9i0j1k-2345-67ab-cdef-8901234567hi', '하의 상품 1', 'BOTTOM', '하의 상품 1 설명', '하의 상품 1의 내용', 30000, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('9i0j1k2l-3456-78ab-cdef-9012345678ij', '하의 상품 2', 'BOTTOM', '하의 상품 2 설명', '하의 상품 2의 내용', 35000, 25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('0j1k2l3m-4567-89ab-cdef-0123456789jk', '하의 상품 3', 'BOTTOM', '하의 상품 3 설명', '하의 상품 3의 내용', 40000, 35, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('1k2l3m4n-5678-90ab-cdef-1234567890lm', '아우터 상품 1', 'OUTER', '아우터 상품 1 설명', '아우터 상품 1의 내용', 50000, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('2l3m4n5o-6789-01ab-cdef-2345678901mn', '아우터 상품 2', 'OUTER', '아우터 상품 2 설명', '아우터 상품 2의 내용', 55000, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('3m4n5o6p-7890-12ab-cdef-3456789012no', '아우터 상품 3', 'OUTER', '아우터 상품 3 설명', '아우터 상품 3의 내용', 60000, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('4n5o6p7q-8901-23ab-cdef-4567890123op', '악세사리 상품 1', 'ACCESSORY', '악세사리 상품 1 설명', '악세사리 상품 1의 내용', 7000, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('5o6p7q8r-9012-34ab-cdef-5678901234pq', '악세사리 상품 2', 'ACCESSORY', '악세사리 상품 2 설명', '악세사리 상품 2의 내용', 7500, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('6p7q8r9s-0123-45ab-cdef-6789012345qr', '악세사리 상품 3', 'ACCESSORY', '악세사리 상품 3 설명', '악세사리 상품 3의 내용', 8000, 70, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--------------------------------------------------------------------------------------------------
TRUNCATE categories;
INSERT INTO `CATEGORIES` (`ID`, `CODE`, `NAME`, `SEQ`, `CREATED_AT`, `UPDATED_AT`) VALUES
('1a2b3c4d-5678-90ab-cdef-1234567890ab', 'TOP', '상의', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('2b3c4d5e-6789-01ab-cdef-2345678901bc', 'BOTTOM', '하의', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('3c4d5e6f-7890-12ab-cdef-3456789012cd', 'OUTER', '아우터', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('4d5e6f7g-8901-23ab-cdef-4567890123de', 'ACCESSORY', '악세사리', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
--------------------------------------------------------------------------------------------------
TRUNCATE orders;
TRUNCATE order_items;
--------------------------------------------------------------------------------------------------
TRUNCATE files;
INSERT INTO `FILES` (`ID`, `PARENT_TABLE`, `PARENT_ID`, `NAME`, `ORIGIN_NAME`, `PATH`, `SIZE`, `IS_MAIN`, `SEQ`, `CREATED_AT`, `UPDATED_AT`) VALUES
('a1b2c3d4-5678-90ab-cdef-111111111111', 'products', '5e6f7g8h-9012-34ab-cdef-5678901234ef', 'TOP1', 'TOP1', 'C:\\upload\\TOP1.jpg', 1000, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('b2c3d4e5-6789-01ab-cdef-222222222222', 'products', '6f7g8h9i-0123-45ab-cdef-6789012345fg', 'TOP2', 'TOP2', 'C:\\upload\\TOP2.jpg', 2000, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('c3d4e5f6-7890-12ab-cdef-333333333333', 'products', '7g8h9i0j-1234-56ab-cdef-7890123456gh', 'TOP3', 'TOP3', 'C:\\upload\\TOP3.jpg', 3000, TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('d4e5f6g7-8901-23ab-cdef-444444444444', 'products', '8h9i0j1k-2345-67ab-cdef-8901234567hi', 'BOTTOM1', 'BOTTOM1', 'C:\\upload\\BOTTOM1.jpg', 4000, TRUE, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('e5f6g7h8-9012-34ab-cdef-555555555555', 'products', '9i0j1k2l-3456-78ab-cdef-9012345678ij', 'BOTTOM2', 'BOTTOM2', 'C:\\upload\\BOTTOM2.jpg', 5000, TRUE, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('f6g7h8i9-0123-45ab-cdef-666666666666', 'products', '0j1k2l3m-4567-89ab-cdef-0123456789jk', 'BOTTOM3', 'BOTTOM3', 'C:\\upload\\BOTTOM3.jpg', 6000, TRUE, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('g7h8i9j0-1234-56ab-cdef-777777777777', 'products', '1k2l3m4n-5678-90ab-cdef-1234567890lm', 'OUTER1', 'OUTER1', 'C:\\upload\\OUTER1.jpg', 7000, TRUE, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('h8i9j0k1-2345-67ab-cdef-888888888888', 'products', '2l3m4n5o-6789-01ab-cdef-2345678901mn', 'OUTER2', 'OUTER2', 'C:\\upload\\OUTER2.jpg', 8000, TRUE, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('i9j0k1l2-3456-78ab-cdef-999999999999', 'products', '3m4n5o6p-7890-12ab-cdef-3456789012no', 'OUTER3', 'OUTER3', 'C:\\upload\\OUTER3.jpg', 9000, TRUE, 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('j0k1l2m3-4567-89ab-cdef-aaaaaaaaaaaa', 'products', '4n5o6p7q-8901-23ab-cdef-4567890123op', 'ACC1', 'ACC1', 'C:\\upload\\ACC1.jpg', 10000, TRUE, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('k1l2m3n4-5678-90ab-cdef-bbbbbbbbbbbb', 'products', '5o6p7q8r-9012-34ab-cdef-5678901234pq', 'ACC2', 'ACC2', 'C:\\upload\\ACC2.jpg', 11000, TRUE, 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('l2m3n4o5-6789-01ab-cdef-cccccccccccc', 'products', '6p7q8r9s-0123-45ab-cdef-6789012345qr', 'ACC3', 'ACC3', 'C:\\upload\\ACC3.jpg', 12000, TRUE, 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



--------------------------------------------------------------------------------------------------
TRUNCATE ADDRESS;
INSERT INTO `ADDRESS` (`ID`, `USER_ID`, `TITLE`, `RECIPIENT`, `ADDRESS`, `ADDRESS_DETAIL`, `PHONE`, `REQUEST`, `IS_DEFAULT`, `ACCESS_NO`, `CREATED_AT`, `UPDATED_AT`)
VALUES
('a1b2c3d4-e5f6-7890-abcd-ef1234567891', 'u1b2c3d4-e5f6-7890-abcd-ef1234567890', '집', '홍길동', '서울특별시 종로구 청와대로 1', '청와대 본관', '010-1234-5678', '문앞', 1, '1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('a1b2c3d4-e5f6-7890-abcd-ef1234567892', 'u1b2c3d4-e5f6-7890-abcd-ef1234567890', '회사', '김철수', '서울특별시 중구 을지로 100', '을지로 위워크 10층', '010-8765-4321', '경비실', 0, '5678', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('a1b2c3d4-e5f6-7890-abcd-ef1234567893', 'u1b2c3d4-e5f6-7890-abcd-ef1234567890', '사무실', '박영희', '서울특별시 강남구 테헤란로 200', '강남 파이낸스 센터 20층', '010-1111-2222', '부재시문앞', 0, '9101', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
