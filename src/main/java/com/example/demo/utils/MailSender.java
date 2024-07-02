package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void sendMail(String email, String subject, String content) {
        System.err.println("send email !!!!!!!!!!!!!!!!");
    }
}
