package com.example.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${mail.to}")
    private String to;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail() throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message, true, "UTF-8");

        // 发件人
        helper.setFrom(from);

        // 收件人
        helper.setTo(to);

        // 邮件标题
        helper.setSubject("每周提醒");

        // 邮件正文
        String content = """
                <html>
                <body>
                    <h2>每周提醒</h2>

                    <p>你好！</p>

                    <p>这是一封自动发送的邮件。</p>

                    <p>
                        本邮件由 Spring Boot + GitHub Actions 自动发送。
                    </p>

                    <p>
                        发送时间：每周一、三、五上午 10:00
                    </p>

                    <p>祝你生活愉快！</p>

                </body>
                </html>
                """;

        // true 表示 HTML 邮件
        helper.setText(content, true);

        // 发送
        mailSender.send(message);

        System.out.println("================================");
        System.out.println("邮件发送成功！");
        System.out.println("收件人：" + to);
        System.out.println("================================");
    }
}