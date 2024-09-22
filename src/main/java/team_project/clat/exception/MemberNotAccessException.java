package team_project.clat.exception;

public class MemberNotAccessException extends RuntimeException{

    public MemberNotAccessException() {
    }

    public MemberNotAccessException(String message) {
        super(message);
    }

    public MemberNotAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotAccessException(Throwable cause) {
        super(cause);
    }
}
