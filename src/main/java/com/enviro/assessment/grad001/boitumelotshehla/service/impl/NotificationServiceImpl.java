package com.enviro.assessment.grad001.boitumelotshehla.service.impl;

import com.enviro.assessment.grad001.boitumelotshehla.exception.NotificationException;
import com.enviro.assessment.grad001.boitumelotshehla.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender emailSender;
    @Override
    public void sendWithdrawalNotification(String firstName, String email , BigDecimal withdrawalAmount, BigDecimal currentBalance, BigDecimal closingBalance) {
        String emailSubject = "Withdrawal Notice";
        String emailBody = """
                Dear %s
                You have made a withdrawal from your account. Details:
                Balance before withdrawal: %s
                Amount withdrawn: %s
                Closing balance: %s
                Thank you for using our services.
                """.formatted(firstName, currentBalance, withdrawalAmount, closingBalance);

        sendEmail(email, emailSubject, emailBody);
    }

    private void sendEmail(String to, String subject, String text) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("withdrawal@enviro365.co.za");
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            emailSender.send(mailMessage);
        } catch (MailException e) {
            throw new NotificationException("Email was not sent");
        }

    }
}
