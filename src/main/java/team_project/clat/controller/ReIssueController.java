package team_project.clat.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team_project.clat.domain.Token;
import team_project.clat.dto.response.CommonResultResDTO;
import team_project.clat.dto.response.ReissueResDTO;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.repository.TokenRepository;
import team_project.clat.service.ReIssueService;

import java.util.Optional;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class ReIssueController {

    private final ReIssueService reIssueService;

    @PostMapping("/reIssue")
    public ResponseEntity<CommonResultResDTO> reIssue(HttpServletRequest request, HttpServletResponse response){

        ReissueResDTO reissueResDTO = reIssueService.tokenReissue(request);

        if(reissueResDTO.getNewAccess()!=null) {
            response.setHeader("access", reissueResDTO.getNewAccess());
            response.setHeader(HttpHeaders.SET_COOKIE, createCookie("refresh", reissueResDTO.getNewRefresh()).toString());
        }

        return new ResponseEntity<>(reissueResDTO.getCommonResultResDTO(), reissueResDTO.getHttpStatus());

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

    }
}
