package team_project.clat.exception;

public class UsernameDataIntegrityViolationException extends RuntimeException{
    public UsernameDataIntegrityViolationException() {
    }

    public UsernameDataIntegrityViolationException(String message) {
        super(message);
    }

    public UsernameDataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDataIntegrityViolationException(Throwable cause) {
        super(cause);
    }
}
