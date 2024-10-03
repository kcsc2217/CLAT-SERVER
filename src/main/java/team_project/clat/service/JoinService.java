package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.domain.Member;
import team_project.clat.dto.response.CommonResultResDTO;
import team_project.clat.dto.request.JoinReqDTO;
import team_project.clat.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public ResponseEntity<CommonResultResDTO> existsByUsername(JoinReqDTO joinReqDTO) {
        if(memberRepository.existsByUsername(joinReqDTO.getUsername())){
            CommonResultResDTO commonResultResDTO = new CommonResultResDTO("409 Conflict", "중복된 ID가 존재합니다.");
            return new ResponseEntity<>(commonResultResDTO, HttpStatus.CONFLICT);
        }else {
            CommonResultResDTO commonResultResDTO = new CommonResultResDTO("200 OK", "사용가능한 ID 입니다.");
            return new ResponseEntity<>(commonResultResDTO, HttpStatus.OK);
        }
    }

    public void joinProcess(JoinReqDTO joinReqDTO, String filePath){

        String username = joinReqDTO.getUsername();
        String password = joinReqDTO.getPassword();
        String name = joinReqDTO.getName();
        String schoolName = joinReqDTO.getSchoolName();
        UserType userType = joinReqDTO.getUserType();

        Boolean isExist = memberRepository.existsByUsername(username);

        if(isExist){

            return;
        }

        log.info("{}", filePath);

        Member member = Member.memberSet(name, username, bCryptPasswordEncoder.encode(password), schoolName, userType, filePath);

        memberRepository.save(member);
    }
}
