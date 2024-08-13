package team_project.clat.Service;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.domain.Member;
import team_project.clat.dto.JoinDto;
import team_project.clat.repository.MemberRepository;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDto joinDto){

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();
        String name = joinDto.getName();
        String schoolName = joinDto.getSchoolName();
        UserType userType = joinDto.getUserType();

        Boolean isExist = memberRepository.existsByUsername(username);

        if(isExist){

            return;
        }

        Member member = Member.memberSet(name, username, bCryptPasswordEncoder.encode(password), schoolName, userType);

        memberRepository.save(member);
    }
}
