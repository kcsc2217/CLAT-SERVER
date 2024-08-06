package team_project.clat.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.domain.Member;
import team_project.clat.dto.JoinDto;
import team_project.clat.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto){

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        Boolean isExist = memberRepository.existsByUsername(username);

        if(isExist){

            return;
        }

        Member member = Member.memberSet(username, bCryptPasswordEncoder.encode(password), UserType.ADMIN);

        memberRepository.save(member);
    }
}
