package com.coatingbazaar.api.service;

import com.coatingbazaar.api.model.EmailRequest;
import jakarta.mail.Address;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Test
    void sendOrderEmailBuildsAndSendsHtmlTable() throws Exception {
        MimeMessage mimeMessage = new MimeMessage(Session.getInstance(new Properties()));
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        EmailService emailService = new EmailService(mailSender, "no-reply@coatingbazaar.local","omnicoatsolution@zohomail.in");
        EmailRequest request = new EmailRequest(
            "order request",
            "15Mt",
            "Gsharp corporation",
            "400080",
            "9833648779",
            "Polyester Resin",
            "TGIC Polyester Resin",
            "Automotive",
            "3AM in Shibuya#225577",
            "Polyester",
            "Smooth",
            "80%"
        );

        emailService.sendOrderEmail(request);
        verify(mailSender).send(mimeMessage);

        Address from = mimeMessage.getFrom()[0];
        Address to = mimeMessage.getAllRecipients()[0];
        String html = extractBodyAsText(mimeMessage);

        assertEquals("no-reply@coatingbazaar.local", ((InternetAddress) from).getAddress());
        assertEquals("omnicoatsolution@zohomail.in", ((InternetAddress) to).getAddress());
        assertEquals("order request", mimeMessage.getSubject());
        assertTrue(html.contains("<table"));
        assertTrue(html.contains("Gsharp corporation"));
        assertTrue(html.contains("TGIC Polyester Resin"));
        assertTrue(html.contains("Polyester"));
        assertTrue(html.contains("Smooth"));
        assertTrue(html.contains("80%"));
    }

    private String extractBodyAsText(MimeMessage mimeMessage) throws Exception {
        Object content = mimeMessage.getContent();
        if (content instanceof String body) {
            return body;
        }
        if (content instanceof Multipart multipart) {
            return multipart.getBodyPart(0).getContent().toString();
        }
        return "";
    }
}


