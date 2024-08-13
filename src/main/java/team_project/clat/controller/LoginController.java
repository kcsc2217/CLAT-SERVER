package team_project.clat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.dto.CommonResult;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<CommonResult> login(){
        CommonResult commonResult = new CommonResult("200 OK", "로그인이 완료되었습니다.");
        return new ResponseEntity<>(commonResult, HttpStatus.OK);
    }

}
