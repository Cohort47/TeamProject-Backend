package org.backend.service.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailUtil {

    private final Configuration freemakerConfiguration;
    private final JavaMailSender javaMailSender;

    private String createConfirmationMail(String firstName, String lastName, String link) {
        try {
            Template template = freemakerConfiguration.getTemplate("confirm_registration_mail.ftlh");
            Map<Object, Object> model = new HashMap<>();
            model.put("firstName", firstName);
            model.put("lastName", lastName);
            model.put("link", link);

            String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            log.info("Generated email content: {}", emailContent);
            return emailContent;
        } catch (Exception e) {
            log.error("Error while creating confirmation mail content: {}", e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public void send(String firstName, String lastName, String link, String subject, String email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            // Прописать данные для письма
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(createConfirmationMail(firstName, lastName, link), true);
            javaMailSender.send(message);
            log.info("Email successfully sent to {}", email); // Добавил логирование успешной отправки
        } catch (MessagingException e) {
            log.error("Error while sending email: {}", e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }
}
