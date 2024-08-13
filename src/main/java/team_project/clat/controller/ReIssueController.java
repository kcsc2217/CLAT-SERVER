package team_project.clat.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.dto.CommonResult;
import team_project.clat.jwt.JwtUtil;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class ReIssueController {

    private final JwtUtil jwtUtil;

    @PostMapping("/reIssue")
    public ResponseEntity<CommonResult> reIssue(HttpServletRequest request, HttpServletResponse response){

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }
        if (refresh == null) {

            //response status code
            CommonResult commonResult = new CommonResult("400 bad_request", "refresh token null.");
            return new ResponseEntity<>(commonResult, HttpStatus.BAD_REQUEST);
        }
        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            CommonResult commonResult = new CommonResult("400 bad_request", "refresh token expired.");
            return new ResponseEntity<>(commonResult, HttpStatus.BAD_REQUEST);
        }
        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            //response status code
            CommonResult commonResult = new CommonResult("400 bad_request", "invalid refresh token.");
            return new ResponseEntity<>(commonResult, HttpStatus.BAD_REQUEST);

        }

        String username = jwtUtil.getUsername(refresh);
        String userType = jwtUtil.getUserType(refresh);


        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, userType , 600000L);

        //response
        response.setHeader("access", newAccess);

        CommonResult commonResult = new CommonResult("200 OK", "토큰 재발급이 완료되었습니다..");
        return new ResponseEntity<>(commonResult, HttpStatus.OK);

    }
}
