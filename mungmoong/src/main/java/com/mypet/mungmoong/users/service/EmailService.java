package com.mypet.mungmoong.users.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);  // SLF4J Logger

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        String from = "jtnewy01@gmail.com";  // 발송자 이메일
        String host = "smtp.gmail.com";  // 이메일 서버
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");  // SSL 포트 465 사용
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // 세션 생성 및 인증 정보
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("jtnewy01@gmail.com", "pcrh ilgp hzoj havy");  // 인증 정보
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            logger.info("Email sent successfully to {}", to);  // 로깅: 성공 메시지
        } catch (MessagingException mex) {
            logger.error("Error in sending email: {}", mex.getMessage());  // 로깅: 에러 메시지
            throw mex;  // 예외를 다시 던지기
        }
    }
}
