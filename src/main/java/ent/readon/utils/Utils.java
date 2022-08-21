package ent.readon.utils;

import ent.readon.dto.user.PasswordResetDto;
import ent.readon.dto.user.UserCreateDto;
import ent.readon.repo.AuthRepo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

@Component
public class Utils {

    final SpringTemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    private final ClassLoader loader = getClass().getClassLoader();

    public Utils(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public boolean validEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).find();
    }

    public Validator<?> validForRegister(UserCreateDto dto, AuthRepo repo) {
        if (Objects.isNull(dto.getUsername())) return new Validator<>("Username required", false);
        if (Objects.isNull(dto.getEmail())) return new Validator<>("Email required", false);
        if (Objects.isNull(dto.getPassword())) return new Validator<>("Password required", false);
        if (!validEmail(dto.getEmail())) return new Validator<>("Email is not valid", false);
        if (repo.existsByEmail(dto.getEmail())) return new Validator<>("Email already taken", false);
        if (repo.existsByUsername(dto.getUsername())) return new Validator<>("Username already taken", false);
        return new Validator<>(true);
    }

    public Validator<String> validForResetPassword(PasswordResetDto dto, AuthRepo repo) {
        if (Objects.isNull(dto.getCode())) return new Validator<>("Confirm code required", false);
        if (Objects.isNull(dto.getEmail())) return new Validator<>("Email required", false);
        if (Objects.isNull(dto.getPassword())) return new Validator<>("New password required", false);
        if (Objects.isNull(dto.getPrePassword())) return new Validator<>("Confirm password required", false);
        if (!dto.getPassword().equals(dto.getPrePassword()))
            return new Validator<>("Passwords didn't match", false);
        if (!repo.existsByEmail(dto.getEmail()))
            return new Validator<>("We do not have a user with this email", false);
        if (!repo.existsByEmailAndEmailCode(dto.getEmail(), dto.getCode().toString()))
            return new Validator<>("Confirmation code incorrect", false);
        if (repo.existsByEmailCodeAndEmailCodeExpiryBefore(dto.getCode().toString(), LocalDateTime.now()))
            return new Validator<>("Confirm code expired!", false);
        return new Validator<>(true);
    }

    public String getSixDigitPassword() {
        Random random = new SecureRandom();
        int nextInt = random.nextInt(999999);
        return String.format("%06d", nextInt);
    }

    public void sendEmailVerification(String email, String code) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        Runnable sender = () -> {
            try {
                String resource = String.format("http://localhost:8090/api/v1/auth/confirmAccount?email=%s&code=%s", email, code);
                Context context = new Context();
                context.setVariable("url", resource);
                String html = templateEngine.process("confirmation", context);
                MimeMessage mime = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mime, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
                helper.setFrom("readooon@gmail.com");
                helper.setTo(email);
                helper.setText(html, true);
                helper.setSubject("Verify your email");
                mime.saveChanges();
                mailSender.send(mime);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        };
        service.execute(sender);
    }

    public void sendResetPasswordConfirmation(String email, String code) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        Runnable sender = () -> {
            try {
                Context context = new Context();
                context.setVariable("code", code);
                String html = templateEngine.process("resetPassword", context);
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
                helper.setFrom("readooon@gmail.com");
                helper.setTo(email);
                helper.setText(html, true);
                helper.setSubject("Password reset code");
                mimeMessage.saveChanges();
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        };
        service.execute(sender);
    }
}
