package team_project.clat.exception;

public class AccessTokenInvalidException extends RuntimeException{

    public AccessTokenInvalidException() {
    }

    public AccessTokenInvalidException(String message) {
        super(message);
    }

    public AccessTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenInvalidException(Throwable cause) {
        super(cause);
    }
}
