package com.cgi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendHtmlMessage(String to, String subject, String evaluatorName, String candidateName, String interviewDate,String meetingLink) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);

            String htmlContent = loadTemplate("scheduletemplate.html");
            htmlContent = replacePlaceholders(htmlContent, Map.of(
                    "evaluatorName", evaluatorName,
                    "candidateName", candidateName,
                    "interviewDate", interviewDate,
                    "meetingLink", meetingLink
            ));
                       
            helper.setText(htmlContent, true);
            ClassPathResource logo = new ClassPathResource("cgi-logo.png");
            helper.addInline("logo", logo);

            emailSender.send(message);
           
            System.out.println("HTML Email successfully sent to " + to);
        } catch (IOException | MessagingException e) {
            System.err.println("Error sending HTML email to " + to + ": " + e.getMessage());
        }
    }
    	

    private String loadTemplate(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        return new String(bytes);
    }

    private String replacePlaceholders(String template, Map<String, String> placeholders) {
        String result = template;
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            result = result.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return result;
    }
	}
