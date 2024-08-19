package team_project.clat.service1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team_project.clat.exception.MailVerifyException;
import team_project.clat.repository.VerificationCodeRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    @Value("${spring.mail.username")
    private String serviceEmail;

    private final JavaMailSender mailSender;
    private final VerificationCodeRepository verificationCodeRepository;

    public void sendSimpleVerificationMail(String to, LocalDateTime sentAt) {

        String empty = verificationCodeRepository.findByTo(to).orElse("empty");
        Optional<VerificationCode> byCode = verificationCodeRepository.findByCode(empty);

        if(byCode.isPresent()){
            throw new MailVerifyException("이미 메일이 발송되었습니다.");
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(serviceEmail);
        mailMessage.setTo(to);
        mailMessage.setSubject(String.format("Email Verification For %s", to));


        VerificationCode verificationCode = new VerificationCode();
        String text = verificationCode.generateCodeMessage();


        verificationCodeRepository.save(verificationCode);
        verificationCodeRepository.toSave(to, text);

        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    public void verifyCode(String to, String code, LocalDateTime verifiedAt) {
        VerificationCode verificationCode = verificationCodeRepository.findByCode(code)
                .orElseThrow(() -> new MailVerifyException("인증코드 올바르지않습니다."));

        if (verificationCode.isExpired(verifiedAt)) {
            verificationCodeRepository.remove(verificationCode);
            verificationCodeRepository.toRemove(to);
            throw new MailVerifyException("인증코드가 만료되었습니다.");
        }

        verificationCodeRepository.toRemove(to);
        verificationCodeRepository.remove(verificationCode);
    }
}
