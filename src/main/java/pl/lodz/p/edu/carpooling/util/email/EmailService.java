package pl.lodz.p.edu.carpooling.util.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.lodz.p.edu.carpooling.exception.email.EmailException;
import pl.lodz.p.edu.carpooling.util.email.message.EmailTemplate;
import pl.lodz.p.edu.carpooling.util.email.message.EmailTemplateFactory;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;
    private final EmailTemplateFactory emailTemplateFactory;
    @Value("${email.username}")
    private String senderEmail;

    public void sendConfirmationRegistrationEmail(String token, String receiverEmail) {
        sendEmailWithMessage(emailTemplateFactory.createRegistrationEmailTemplate(token), receiverEmail);
    }

    public void sendResetPasswordEmail(String token, String receiverEmail) {
        sendEmailWithMessage(emailTemplateFactory.createResetPasswordEmailTemplate(token), receiverEmail);
    }

    public void sendDirectionHasBeenDeletedByTheCreatorEmailForAssignedUser(String receiverEmail, String directionId) {
        sendEmailWithMessage(emailTemplateFactory.createDirectionDeletedEmailTemplate(directionId), receiverEmail);
    }

    private void sendEmailWithMessage(EmailTemplate emailTemplate, String receiverEmail) {
        Thread emailSenderThread = new Thread(() -> {
            try {
                MimeMessage mimeMessage = sender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setFrom(senderEmail);
                mimeMessageHelper.setTo(receiverEmail);
                mimeMessageHelper.setSubject(emailTemplate.getSubject());
                mimeMessageHelper.setText(emailTemplate.getBody(), true);
                sender.send(mimeMessage);
            } catch (MessagingException e) {
                throw EmailException.createSendingEmailErrorException(e.getCause());
            }
        });
        emailSenderThread.start();
    }
}
