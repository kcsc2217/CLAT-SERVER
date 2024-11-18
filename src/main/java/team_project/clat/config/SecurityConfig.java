package team_project.clat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import team_project.clat.jwt.JwtFilter;
import team_project.clat.jwt.JwtUtil;
import team_project.clat.jwt.LoginFilter;
import team_project.clat.jwt.CustomLogoutFilter;
import team_project.clat.repository.TokenRepository;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    //JWTUtil 주입
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final TokenRepository tokenRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://c-lat.site", "https://clat-client.vercel.app"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("Authorization", "access", "Content-Type","Set-Cookie"));
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L);

                configuration.setExposedHeaders(List.of("Authorization", "access","Set-Cookie"));


                return configuration;
            }
        })));

        //csrf disable
        http.csrf((auth) -> auth.disable());

        //Form 로그인 방식 disable
        http.formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        //경로별 인가 작업
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/v3/api-docs/**","/api/image","/login", "/swagger-ui/**", "/","index.html", "/join","/verify-email", "/verification-code", "/idCheck", "/help/**", "/api/download","/logout").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers( "/chatRoom").hasRole("PROFESSOR")
                        .requestMatchers("/chatRoom/api/**").hasRole("PROFESSOR")
                        .requestMatchers("/reIssue", "/delete", "/member/findPwd").permitAll()
                        .anyRequest().authenticated());


        http.addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class);


        //AuthenticationManager()와 JWTUtil 인수 전달
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, objectMapper, tokenRepository), UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new CustomLogoutFilter(jwtUtil, tokenRepository, objectMapper), LogoutFilter.class);

        //세션 설정
        http.sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

}
