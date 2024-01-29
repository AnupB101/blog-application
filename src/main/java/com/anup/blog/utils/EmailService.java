package com.anup.blog.utils;

import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


public class EmailService implements CommandLineRunner {
    @Autowired
    private JavaMailSender javamailSender;
    @Value("${spring.mail.username}")
    private String sender;
        public static void main(String[] args) {
            SpringApplication.run(EmailService.class, args);
        }

        @Override
        public void run (String...args) throws Exception {
            System.out.println("..sending email...");
            sendEmail();

        }
        void sendEmail ()
        {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(sender);
            msg.setTo("bannetanup@gmail.com");
            msg.setSubject("What is this code");
            msg.setText("Practice Twice");
            javamailSender.send(msg);
        }

    }

