package com.atastaff.absencesystem.service;

import com.atastaff.absencesystem.model.Class;
import com.atastaff.absencesystem.model.Student;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Service class responsible for sending email notifications to parents
 * regarding their child's absence.
 */
@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") // Sender email address configured in application.properties
    private String senderEmail;

    /**
     * Constructor for NotificationService, injecting the JavaMailSender.
     *
     * @param javaMailSender The Spring Mail sender used to send emails.
     */
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Sends an absence notification email to the parent of the specified student.
     *
     * @param student The student who is absent.
     * @param classEntity The class for which the student is absent.
     * @param absenceDate The date of the absence.
     * @throws MessagingException if there is an error creating the MimeMessage.
     * @throws MailException if there is an error sending the email.
     */
    public void sendAbsenceNotification(Student student, Class classEntity, LocalDate absenceDate) throws MessagingException, MailException {
        // Validate input
        if (student == null || classEntity == null || absenceDate == null) {
            logger.error("Cannot send absence notification: student, class, or absence date is null.");
            throw new IllegalArgumentException("Student, class, and absence date must not be null for notification.");
        }
        if (student.getParentEmail() == null || student.getParentEmail().isEmpty()) {
            logger.warn("Student {} (ID: {}) has no parent email. Skipping notification.", student.getFirstName() + " " + student.getLastName(), student.getStudentId());
            return;
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // true for multipart message

        String studentFullName = student.getFirstName() + " " + student.getLastName();
        String formattedAbsenceDate = absenceDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Set email properties
        helper.setFrom(senderEmail);
        helper.setTo(student.getParentEmail());
        helper.setSubject("Absence Notification: " + studentFullName);

        // Construct email body
        String emailBody = String.format(
            "Dear Parent/Guardian of %s,\n\n" +
            "This is an automated notification to inform you that your child, %s, " +
            "was marked absent from their class '%s' (ID: %s) on %s.\n\n" +
            "Please contact the school administration if you have any questions or require further clarification.\n\n" +
            "Sincerely,\n" +
            "ATA Staff",
            studentFullName, studentFullName, classEntity.getClassName(), classEntity.getClassId(), formattedAbsenceDate
        );
        helper.setText(emailBody);

        try {
            javaMailSender.send(message);
            logger.info("Absence notification sent successfully to {} for student {} on {}",
                    student.getParentEmail(), studentFullName, formattedAbsenceDate);
        } catch (MailException e) {
            logger.error("Failed to send absence notification to {} for student {} on {}: {}",
                    student.getParentEmail(), studentFullName, formattedAbsenceDate, e.getMessage());
            throw e; // Re-throw to allow calling service to handle transaction rollback if needed
        }
    }
}