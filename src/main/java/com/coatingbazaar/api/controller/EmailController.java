package com.coatingbazaar.api.controller;

import com.coatingbazaar.api.model.EmailRequest;
import com.coatingbazaar.api.model.EnquiryRequest;
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
                "message", "Fields   'contactnumber', 'email',  are required"
            ));
        }

        emailService.sendOrderEmail(request);
        return ResponseEntity.ok(Map.of(
            "status", "sent",
            "message", "Email queued to MailHog"
        ));
    }

    @PostMapping("/enquire")
    public ResponseEntity<Map<String, String>> sendEnquiry(@RequestBody EnquiryRequest request) {
        if (hasMissingRequiredFieldsForEnquiry(request)) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", "Fields 'companyname', 'contactnumber', 'email', 'requirement' are required"
            ));
        }

        emailService.sendEnquiryEmail(request);
        return ResponseEntity.ok(Map.of(
            "status", "sent",
            "message", "Enquiry email queued to MailHog"
        ));
    }

    private boolean hasMissingRequiredFields(EmailRequest request) {
        return isBlank(request.email())
            || isBlank(request.companyname())
            || isBlank(request.contactnumber());
    }

    private boolean hasMissingRequiredFieldsForEnquiry(EnquiryRequest request) {
        return isBlank(request.email())
            || isBlank(request.companyname()) || isBlank(request.requirement())
            || isBlank(request.contactnumber());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
