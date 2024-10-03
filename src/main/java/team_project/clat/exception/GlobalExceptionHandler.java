package team_project.clat.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import team_project.clat.dto.Dto.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(GlobalException.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(
          HttpServletRequest request, GlobalException e
  ) {
    return ResponseEntity.status(e.getErrorCode().getHttpStatus())
            .body(new ErrorResponse(
                            e.getErrorCode().getDescription(),
                            e.getErrorCode().getHttpStatus().value(),
                            e.getErrorCode().getErrorCode(),
                            request.getRequestURI()
                    )
            );
  }
}