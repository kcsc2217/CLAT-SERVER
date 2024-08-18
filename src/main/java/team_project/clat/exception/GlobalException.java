package team_project.clat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team_project.clat.type.ErrorCode;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {
  private final ErrorCode errorCode;
}