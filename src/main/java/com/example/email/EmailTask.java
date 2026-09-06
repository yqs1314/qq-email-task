package com.example.email;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmailTask implements CommandLineRunner {

    private final EmailService emailService;

    public EmailTask(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("开始发送邮件...");

        emailService.sendEmail();

        System.out.println("邮件任务执行完成。");
    }
}