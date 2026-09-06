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
        helper.setSubject("关闭所有钱包续费功能");

        // 邮件正文
        String content = """
                <html>
                <body>
                    姓名：杨全胜<br>
                    手机号：18790184684<br>
                    身份证：411528199811095894
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