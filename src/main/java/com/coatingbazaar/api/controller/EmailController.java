package com.coatingbazaar.api.controller;

import com.coatingbazaar.api.model.EmailRequest;
import com.coatingbazaar.api.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailRequest request) {
        if (hasMissingRequiredFields(request)) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", "Fields 'subject', 'companyname', 'quantity', 'pincode', 'contactnumber', 'product', 'industry', 'colour', 'finish' are required"
            ));
        }

        emailService.sendOrderEmail(request);
        return ResponseEntity.ok(Map.of(
            "status", "sent",
            "message", "Email queued to MailHog"
        ));
    }

    private boolean hasMissingRequiredFields(EmailRequest request) {
        return isBlank(request.subject())
            || isBlank(request.companyname())
            || isBlank(request.quantity())
            || isBlank(request.pincode())
            || isBlank(request.contactnumber())
            || isBlank(request.product())
            || isBlank(request.industry())
            || isBlank(request.colour())
            || isBlank(request.gloss());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

