package com.reservationappbus.util;

import jakarta.mail.MessagingException;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendTicketEmail(String to, String subject, byte[] pdfAttachment, String attachmentName,String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfAttachment, "application/pdf");
            helper.addAttachment(attachmentName, dataSource);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
