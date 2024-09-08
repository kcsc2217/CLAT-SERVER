package team_project.clat.exception;

public class DuplicateAnswerException extends RuntimeException{
    public DuplicateAnswerException() {
    }

    public DuplicateAnswerException(String message) {
        super(message);
    }

    public DuplicateAnswerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAnswerException(Throwable cause) {
        super(cause);
    }
}
