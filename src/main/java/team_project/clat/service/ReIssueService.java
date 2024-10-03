package team_project.clat.service;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import team_project.clat.domain.Token;
import team_project.clat.dto.response.CommonResultResDTO;
import team_project.clat.dto.response.ReissueResDTO;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.repository.TokenRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReIssueService {

    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    public ReissueResDTO tokenReissue(HttpServletRequest request){

        //get refresh token
        String refresh = null;

        if(request.getCookies()==null){
            CommonResultResDTO commonResultResDTO = new CommonResultResDTO("400 bad_request", "refresh token null.");
            return new ReissueResDTO(commonResultResDTO, null, null, HttpStatus.BAD_REQUEST);
        }

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {
            //response status code
            CommonResultResDTO commonResultResDTO = new CommonResultResDTO("400 bad_request", "refresh token null.");
            return new ReissueResDTO(commonResultResDTO, null, null, HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            //response status code
            CommonResultResDTO commonResultResDTO = new CommonResultResDTO("400 bad_request", "refresh token expired.");
            return new ReissueResDTO(commonResultResDTO, null, null, HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {
            //response status code
            CommonResultResDTO commonResultResDTO = new CommonResultResDTO("400 bad_request", "invalid refresh token.");
            return new ReissueResDTO(commonResultResDTO, null, null, HttpStatus.BAD_REQUEST);

        }

        String username = jwtUtil.getUsername(refresh);
        String userType = jwtUtil.getUserType(refresh);

        boolean isExist = tokenRepository.existsById(username);
        if(!isExist){
            CommonResultResDTO commonResultResDTO = new CommonResultResDTO("400 bad_request", "invalid refresh token.");
            return new ReissueResDTO(commonResultResDTO, null, null, HttpStatus.BAD_REQUEST);
        }

        Optional<Token> byId = tokenRepository.findById(username);
        Token token = byId.get();

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, userType , 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, userType, 86400000L);

        tokenRepository.deleteById(username);
        Token refreshToken = new Token(username, newRefresh, 86400000L);
        tokenRepository.save(refreshToken);

        CommonResultResDTO commonResultResDTO = new CommonResultResDTO("200 OK", "토큰 재발급이 완료되었습니다..");

        return new ReissueResDTO(commonResultResDTO, newAccess, newRefresh, HttpStatus.OK);
    }
}
