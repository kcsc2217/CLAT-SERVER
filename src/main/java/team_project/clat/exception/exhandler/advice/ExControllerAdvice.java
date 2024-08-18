package team_project.clat.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team_project.clat.domain.Dto.response.RestResponse;
import team_project.clat.dto.CommonResult;
import team_project.clat.exception.DuplicateCourseChatRoomException;
import team_project.clat.exception.MailVerifyException;
import team_project.clat.exception.NotFoundException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public CommonResult exHandle(MailVerifyException e){
        String errorText = HttpStatus.UNAUTHORIZED.value() +" "+ HttpStatus.UNAUTHORIZED.getReasonPhrase();
        return new CommonResult(errorText, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class) // 타입 매핑이 안맞을때
    public RestResponse validationException(MethodArgumentNotValidException ex){
        log.error("[exceptionHandle] ex", ex);

        return new RestResponse("BAD", ex.getMessage());

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class) //해당 강의를 찾을수 없을떄
    public RestResponse courseNotFoundException(NotFoundException ex){
        log.error("[exceptionHandle] ex", ex);

        return new RestResponse("NOT_FOUND", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateCourseChatRoomException.class)
    public RestResponse duplicateCourseChatRoomException(DuplicateCourseChatRoomException ex){
        log.error("[exceptionHandle] ex", ex);
        return new RestResponse("CONFLICT", ex.getMessage());
    }

}
