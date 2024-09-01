package team_project.clat.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import team_project.clat.domain.Dto.response.RestResponse;
import team_project.clat.dto.CommonResult;
import team_project.clat.exception.*;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public CommonResult exHandle(MailVerifyException e){
        String errorText = HttpStatus.CONFLICT.value() +" "+ HttpStatus.CONFLICT.getReasonPhrase();
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FileSizeLimitException.class)
    public RestResponse fileException(FileSizeLimitException ex){
        log.error("[exceptionHandle] ex", ex);
        return new RestResponse("Bad", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessTokenInvalidException.class)
    public RestResponse tokenException(AccessTokenInvalidException ex){
        log.error("[exceptionHandle] ex", ex);
        return new RestResponse("UNAUTHORIZED", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String MaxUploadSIze(MaxUploadSizeExceededException ex){
        log.error("[exceptionHandle] ex", ex);
        return "file size  exceeds the maximum allowed limit!";
    }




}
