package tn.esprit.emailservice;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessageHelper message = new MimeMessageHelper(emailSender.createMimeMessage(), true);
            message.setFrom("hammoudi.hayfa@esprit.tn");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message.getMimeMessage());
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
        }
    }

}
