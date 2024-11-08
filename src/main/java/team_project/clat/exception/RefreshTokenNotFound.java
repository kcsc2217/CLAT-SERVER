package team_project.clat.exception;

public class RefreshTokenNotFound extends RuntimeException{
    public RefreshTokenNotFound() {
        super();
    }

    public RefreshTokenNotFound(String message) {
        super(message);
    }

    public RefreshTokenNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshTokenNotFound(Throwable cause) {
        super(cause);
    }
}
