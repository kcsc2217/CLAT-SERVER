package team_project.clat.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team_project.clat.domain.Dto.response.RestResponse;
import team_project.clat.exception.CourseNotFoundException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse validationException(MethodArgumentNotValidException ex){
        log.error("[exceptionHandle] ex", ex);

        return new RestResponse("BAD", ex.getMessage());

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CourseNotFoundException.class)
    public RestResponse courseNotFoundException(CourseNotFoundException ex){
        log.error("[exceptionHandle] ex", ex);

        return new RestResponse("NOT_FOUND", ex.getMessage());
    }




}
