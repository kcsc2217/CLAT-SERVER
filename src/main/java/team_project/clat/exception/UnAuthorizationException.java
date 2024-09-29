package team_project.clat.exception;

public class UnAuthorizationException extends RuntimeException{

    public UnAuthorizationException() {
    }

    public UnAuthorizationException(String message) {
        super(message);
    }

    public UnAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizationException(Throwable cause) {
        super(cause);
    }
}
