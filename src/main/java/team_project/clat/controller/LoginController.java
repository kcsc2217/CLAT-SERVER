package team_project.clat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.dto.response.CommonResultResDTO;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<CommonResultResDTO> login(){
        CommonResultResDTO commonResultResDTO = new CommonResultResDTO("200 OK", "로그인이 완료되었습니다.");
        return new ResponseEntity<>(commonResultResDTO, HttpStatus.OK);
    }

}
