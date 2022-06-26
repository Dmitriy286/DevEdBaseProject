package com.example.devedbaseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailAdress, String subj, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(username);
        email.setTo(emailAdress);
        email.setSubject(subj);
        email.setText(text);

        sender.send(email);

    }
}
