package pl.kenez.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import pl.kenez.model.MailModel;

@Service
public class MailService {
    private final Logger logger = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSenderImpl mailSender;

    @Autowired
    public MailService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void send(final MailModel mail) {
        try {
            final SimpleMailMessage mailMessage = createMailMessage(mail);
            mailSender.send(mailMessage);
            logger.info("Email has been sent");
        } catch (MailException e) {
            logger.error("Email not sent");
        }
    }


    private SimpleMailMessage createMailMessage(final MailModel mail) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getContent());
        return mailMessage;
    }
}
