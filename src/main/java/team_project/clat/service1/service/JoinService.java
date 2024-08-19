package team_project.clat.service1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.domain.Member;
import team_project.clat.dto.CommonResult;
import team_project.clat.dto.JoinDto;
import team_project.clat.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public ResponseEntity<CommonResult> existsByUsername(JoinDto joinDto) {
        if(memberRepository.existsByUsername(joinDto.getUsername())){
            CommonResult commonResult = new CommonResult("409 Conflict", "중복된 ID가 존재합니다.");
            return new ResponseEntity<>(commonResult, HttpStatus.CONFLICT);
        }else {
            CommonResult commonResult = new CommonResult("200 OK", "사용가능한 ID 입니다.");
            return new ResponseEntity<>(commonResult, HttpStatus.OK);
        }
    }

    public void joinProcess(JoinDto joinDto, String filePath){

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();
        String name = joinDto.getName();
        String schoolName = joinDto.getSchoolName();
        UserType userType = joinDto.getUserType();

        Boolean isExist = memberRepository.existsByUsername(username);

        if(isExist){

            return;
        }

        log.info("{}", filePath);

        Member member = Member.memberSet(name, username, bCryptPasswordEncoder.encode(password), schoolName, userType, filePath);

        memberRepository.save(member);
    }
}
