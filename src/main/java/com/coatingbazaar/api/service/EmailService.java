package com.coatingbazaar.api.service;

import com.coatingbazaar.api.model.EmailRequest;
import com.coatingbazaar.api.model.EnquiryRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final String defaultFrom;
    private final String to;

    public EmailService(JavaMailSender mailSender,
                        @Value("${app.mail.from}") String defaultFrom,
                        @Value("${app.mail.to}") String to) {
        this.mailSender = mailSender;
        this.defaultFrom = defaultFrom;

        this.to = to;
    }

    public void sendOrderEmail(EmailRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            helper.setFrom(defaultFrom);
            helper.setTo(to);
            helper.setSubject(request.subject());
            helper.setText(buildOrderTableHtml(request), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            throw new IllegalStateException("Failed to compose email", ex);
        }
    }

    public void sendEnquiryEmail(EnquiryRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            helper.setFrom(defaultFrom);
            helper.setTo(to);
            helper.setSubject(request.subject());
            helper.setText(buildEnquiryTableHtml(request), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            throw new IllegalStateException("Failed to compose enquiry email", ex);
        }
    }

    private String buildOrderTableHtml(EmailRequest request) {
        return """
            <html>
              <body style="margin:0; padding:0; font-family: Arial, sans-serif; color: #1f2937; background:#eef3f8;">
                <div style="padding:28px 12px; background:linear-gradient(135deg, #0b2134, #173b56);">
                  <div style="max-width:700px; margin:0 auto;">
                    <div style="background:#ffffff; border:1px solid #dbe5ef; border-radius:14px; overflow:hidden;">
                      <div style="padding:16px 20px; background:linear-gradient(135deg, #0e2b42, #1a4564);">
                        <span style="display:inline-block; width:34px; height:34px; line-height:34px; text-align:center; border-radius:8px; background:#12a8f8; color:#ffffff; font-weight:700; margin-right:8px;">CB</span>
                        <span style="color:#ffffff; font-size:24px; font-weight:700; vertical-align:middle;">Coating<span style="color:#12a8f8;">Bazaar</span></span>
                        <div style="color:#d7e5f4; font-size:13px; margin-top:10px;">India's Largest Powder Coating Raw Materials Platform</div>
                      </div>
                      <div style="padding:20px;">
                        <h2 style="margin:0 0 12px 0; color:#0f2740;">New Order Request</h2>
                        <table style="border-collapse: collapse; width: 100%%; max-width: 640px;">
                          <tr><th style="text-align:left; border:1px solid #d1d5db; padding:8px; background:#f3f4f6;">Field</th><th style="text-align:left; border:1px solid #d1d5db; padding:8px; background:#f3f4f6;">Value</th></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Company Name</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Quantity</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Pincode</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Contact Number</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Category</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Product</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Industry</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Colour</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Chemistry</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Finish</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Gloss</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px;">Email</td><td style="border:1px solid #d1d5db; padding:8px;">%s</td></tr>
                        </table>
                      </div>
                    </div>
                    <div style="text-align:center; color:#c2d4e3; font-size:12px; margin-top:10px;">CoatingBazaar enquiry notification</div>
                  </div>
                </div>
              </body>
            </html>
            """.formatted(
            escapeHtml(request.companyname()),
            escapeHtml(request.quantity()),
            escapeHtml(request.pincode()),
            escapeHtml(request.contactnumber()),
            escapeHtml(request.category()),
            escapeHtml(request.product()),
            escapeHtml(request.industry()),
            escapeHtml(request.colour()),
            escapeHtml(request.chemistry()),
            escapeHtml(request.finish()),
            escapeHtml(request.gloss()),
            escapeHtml(request.email())
        );
    }

    private String buildEnquiryTableHtml(EnquiryRequest request) {
        return """
            <html>
              <body style="margin:0; padding:0; font-family: Arial, sans-serif; color: #1f2937; background:#eef3f8;">
                <div style="padding:28px 12px; background:linear-gradient(135deg, #0b2134, #173b56);">
                  <div style="max-width:700px; margin:0 auto;">
                    <div style="background:#ffffff; border:1px solid #dbe5ef; border-radius:14px; overflow:hidden;">
                      <div style="padding:16px 20px; background:linear-gradient(135deg, #0e2b42, #1a4564);">
                        <span style="display:inline-block; width:34px; height:34px; line-height:34px; text-align:center; border-radius:8px; background:#12a8f8; color:#ffffff; font-weight:700; margin-right:8px;">CB</span>
                        <span style="color:#ffffff; font-size:24px; font-weight:700; vertical-align:middle;">Coating<span style="color:#12a8f8;">Bazaar</span></span>
                        <div style="color:#d7e5f4; font-size:13px; margin-top:10px;">India's Largest Powder Coating Raw Materials Platform</div>
                      </div>
                      <div style="padding:20px;">
                        <h2 style="margin:0 0 12px 0; color:#0f2740;">New Enquiry Request</h2>
                        <table style="border-collapse: collapse; width: 100%%; max-width: 640px; table-layout: fixed;">
                          <tr><th style="text-align:left; border:1px solid #d1d5db; padding:8px; background:#f3f4f6; width: 150px;">Field</th><th style="text-align:left; border:1px solid #d1d5db; padding:8px; background:#f3f4f6;">Value</th></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Subject</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Company Name</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Sector</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Contact Number</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Contact Person</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Surface</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Environment</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Requirement</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word; line-height: 1.5;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Quantity</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Timeline</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                          <tr><td style="border:1px solid #d1d5db; padding:8px; vertical-align:top;">Email</td><td style="border:1px solid #d1d5db; padding:8px; word-wrap: break-word; white-space: pre-wrap; overflow-wrap: break-word;">%s</td></tr>
                        </table>
                      </div>
                    </div>
                    <div style="text-align:center; color:#c2d4e3; font-size:12px; margin-top:10px;">CoatingBazaar enquiry notification</div>
                  </div>
                </div>
              </body>
            </html>
            """.formatted(
            escapeHtml(request.subject()),
            escapeHtml(request.companyname()),
            escapeHtml(request.sector()),
            escapeHtml(request.contactnumber()),
            escapeHtml(request.contactperson()),
            escapeHtml(request.surface()),
            escapeHtml(request.environment()),
            escapeHtml(request.requirement()),
            escapeHtml(request.quantity()),
            escapeHtml(request.timeline()),
            escapeHtml(request.email())
        );
    }

    private String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
    }
}
