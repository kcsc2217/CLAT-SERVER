package team_project.clat.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team_project.clat.Service.EmailService;
import team_project.clat.Service.JoinService;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.dto.CommonResult;
import team_project.clat.dto.EmailRequest;
import team_project.clat.dto.JoinDto;
import team_project.clat.dto.JoinResult;
import team_project.clat.jwt.JwtUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final JoinService joinService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

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
    public ResponseEntity<JoinResult> joinProcess(@RequestPart JoinDto joinDto,
                                                    @RequestPart MultipartFile file,
                                                    HttpServletResponse response) throws IOException {

        String fileDir = "/Users/akk/Desktop/test/";
        String fullPath = null;
        if(!file.isEmpty()){
            fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }

        joinService.joinProcess(joinDto, fullPath);

        String username = joinDto.getUsername();
        String role = joinDto.getUserType().getDescription();

        //토큰 생성
        String access = jwtUtil.createJwt("access", username, role, 600000L);
        String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //응답 설정
        response.setHeader("access", access);
        response.addCookie(createCookie("refresh", refresh));
        response.setStatus(HttpStatus.OK.value());

        JoinResult joinResult = new JoinResult("200 OK", "회원가입이 완료되었습니다.", joinDto.getName());
        return new ResponseEntity<>(joinResult, HttpStatus.OK);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true); //https로 진행할 시
        //cookie.setPath("/"); //쿠키가 적용될 범위
        cookie.setHttpOnly(true);

        return cookie;
    }
}
