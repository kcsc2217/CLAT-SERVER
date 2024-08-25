package team_project.clat.exception;

public class FileSizeLimitException extends RuntimeException{
    public FileSizeLimitException() {
    }

    public FileSizeLimitException(String message) {
        super(message);
    }

    public FileSizeLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeLimitException(Throwable cause) {
        super(cause);
    }
}
