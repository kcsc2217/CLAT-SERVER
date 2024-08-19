package team_project.clat.service1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team_project.clat.domain.Member;
import team_project.clat.dto.CustomUserDetails;
import team_project.clat.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member userData = memberRepository.findByUsername(username);

        if(username != null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
