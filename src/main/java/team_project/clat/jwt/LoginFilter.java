package team_project.clat.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.dto.CustomUserDetails;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        log.info("username : {}", username);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //유저 정보
        String username = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        UserType userType1 = null;
        for (UserType userType : UserType.values()) {
            if (userType.getDescription().equalsIgnoreCase(role)) {
                userType1 = userType;
            }
        }
            //토큰 생성
            String access = jwtUtil.createJwt("access", username, userType1, 600000L);
            String refresh = jwtUtil.createJwt("refresh", username, userType1, 86400000L);

            //응답 설정
            response.setHeader("access", access);
            response.addCookie(createCookie("refresh", refresh));
            response.setStatus(HttpStatus.OK.value());

            chain.doFilter(request, response);
        }

        @Override
        protected void unsuccessfulAuthentication (HttpServletRequest request, HttpServletResponse
        response, AuthenticationException failed) throws IOException, ServletException {
            response.setStatus(401);
        }

        private Cookie createCookie (String key, String value){

            Cookie cookie = new Cookie(key, value);
            cookie.setMaxAge(24 * 60 * 60);
            //cookie.setSecure(true); //https로 진행할 시
            //cookie.setPath("/"); //쿠키가 적용될 범위
            cookie.setHttpOnly(true);

            return cookie;
        }
    }
