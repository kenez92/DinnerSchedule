package pl.kenez.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${mail.name}")
    private String name;

    @Value("${mail.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl configure() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        mailSender.setJavaMailProperties(properties);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(name);
        mailSender.setPassword(password);
        return mailSender;
    }
}
