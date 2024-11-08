package team_project.clat.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.dto.request.DeleteMemberReqDTO;
import team_project.clat.dto.response.CommonResultResDTO;
import team_project.clat.dto.response.DeleteMemberResDTO;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.service.DeleteMemberService;

@RestController
@RequiredArgsConstructor
public class DeleteMemberController {

    private final DeleteMemberService deleteMemberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMember(@RequestBody DeleteMemberReqDTO deleteMemberReqDTO,
                                          HttpServletRequest request, HttpServletResponse response){

        String accessToken = request.getHeader("access");
        String username = jwtUtil.getUsername(accessToken);

        DeleteMemberResDTO deleteMemberResDTO = deleteMemberService.deleteUser(deleteMemberReqDTO, username);

        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        CommonResultResDTO commonResultResDTO = deleteMemberResDTO.getCommonResultResDTO();

        return new ResponseEntity<>(commonResultResDTO, deleteMemberResDTO.getHttpStatus());
    }
}
