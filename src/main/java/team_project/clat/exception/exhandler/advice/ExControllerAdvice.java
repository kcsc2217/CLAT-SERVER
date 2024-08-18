package team_project.clat.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team_project.clat.dto.CommonResult;
import team_project.clat.exception.MailVerifyException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public CommonResult exHandle(MailVerifyException e){
        String errorText = HttpStatus.UNAUTHORIZED.value() +" "+ HttpStatus.UNAUTHORIZED.getReasonPhrase();
        return new CommonResult(errorText, e.getMessage());
    }

}
