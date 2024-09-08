package team_project.clat.exception;

public class ProfessorAuthorizationException extends RuntimeException {
    public ProfessorAuthorizationException() {
    }

    public ProfessorAuthorizationException(String message) {
        super(message);
    }

    public ProfessorAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfessorAuthorizationException(Throwable cause) {
        super(cause);
    }
}
