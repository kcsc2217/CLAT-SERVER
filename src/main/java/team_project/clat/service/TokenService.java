package team_project.clat.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import team_project.clat.domain.Member;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.repository.MemberRepository;

@Component
@RequiredArgsConstructor
@Slf4j

public class TokenService {

    private final JwtUtil jwtUtil;

    private final MemberRepository memberRepository;

    public Member getUsernameFromToken(HttpServletRequest request){
        String accessToken = request.getHeader("access");
        String username = jwtUtil.getUsername(accessToken);

        return memberRepository.findByUsername(username);
    }

    public String getUserTypeFromToken(HttpServletRequest request){
        String accessToken = request.getHeader("access");
        return jwtUtil.getUserType(accessToken);
    }

}
