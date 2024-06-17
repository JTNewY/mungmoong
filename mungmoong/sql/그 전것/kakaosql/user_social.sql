-- Active: 1715129092400@@127.0.0.1@3306@joeun
DROP TABLE IF EXISTS user_social;

TRUNCATE user_social;

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



select * from user_social;



SELECT username
    FROM user_social
WHERE provider = 'kakao'
    AND social_id = '2630976312'
    ;
