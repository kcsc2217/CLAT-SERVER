package team_project.clat.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import team_project.clat.domain.Token;
import team_project.clat.repository.TokenRepository;
import team_project.clat.service.EmailService;
import team_project.clat.service.JoinService;

import team_project.clat.dto.*;
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
    private final TokenRepository tokenRepository;

    @PostMapping("/idCheck")
    public ResponseEntity<CommonResult> isDuplicateUsername(@RequestBody JoinDto joinDto){
        return joinService.existsByUsername(joinDto);
    }

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
        emailService.verifyCode(request.getEmail(), request.getCode(), requestedAt);
        CommonResult commonResult = new CommonResult("200 OK", "인증이 완료되었습니다.");
        return new ResponseEntity<>(commonResult, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(@Valid @RequestPart JoinDto joinDto,
                                                    BindingResult bindingResult,
                                                    @RequestPart MultipartFile file,
                                                    HttpServletResponse response) throws IOException {

        log.info("{},{},{},{}",joinDto.getName(),joinDto.getSchoolName(),joinDto.getUserType(),joinDto.getUsername());

        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            CommonResult commonResult = new CommonResult("400 BAD_REQUEST", errorMessage);
            return new ResponseEntity<>(commonResult, HttpStatus.BAD_REQUEST);
        }

        String fileDir = "/home/ubuntu/upload/";
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

        Token token = new Token(username, refresh, 86400000L);
        tokenRepository.save(token);

        //응답 설정
        response.setHeader("access", access);
        response.setHeader(HttpHeaders.SET_COOKIE, createCookie("refresh", refresh).toString());
        response.setStatus(HttpStatus.OK.value());

        JoinResult joinResult = new JoinResult("200 OK", "회원가입이 완료되었습니다.", joinDto.getName());
        return new ResponseEntity<>(joinResult, HttpStatus.OK);
    }

    private ResponseCookie createCookie(String key, String value) {

        return ResponseCookie
                .from(key, value)
                .path("/")
                .secure(true)
                .httpOnly(true)
                .maxAge(24*60*60)
                .sameSite("None")
                .build();

        /*Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);*/
        //return cookie;
    }
}
