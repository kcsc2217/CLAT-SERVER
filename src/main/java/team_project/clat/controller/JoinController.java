package team_project.clat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import team_project.clat.Service.EmailService;
import team_project.clat.Service.JoinService;
import team_project.clat.dto.CommonResult;
import team_project.clat.dto.EmailRequest;
import team_project.clat.dto.JoinDto;

import java.time.LocalDateTime;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;
    private final EmailService emailService;

    @PostMapping("/verify-email")
    public ResponseEntity<CommonResult> getEmailForVerification(@RequestBody EmailRequest.EmailForVerificationRequest request){
        LocalDateTime requestedAt = LocalDateTime.now();
        emailService.sendSimpleVerificationMail(request.getEmail(), requestedAt);
        CommonResult commonResult = new CommonResult("200 OK", "메일이 발송되었습니다.");
        return new ResponseEntity<>(commonResult, HttpStatus.OK);
    }

    @PostMapping("/verification-code")
    public ResponseEntity<CommonResult> verificationByCode(@RequestBody EmailRequest.VerificationCodeRequest request) {
        LocalDateTime requestedAt = LocalDateTime.now();
        emailService.verifyCode(request.getCode(), requestedAt);
        CommonResult commonResult = new CommonResult("200 OK", "인증이 완료되었습니다.");
        return new ResponseEntity<>(commonResult, HttpStatus.OK);
    }

    @PostMapping("/join")
    public String joinProcess(JoinDto joinDto){

        joinService.joinProcess(joinDto);

        return "ok";
    }
}
