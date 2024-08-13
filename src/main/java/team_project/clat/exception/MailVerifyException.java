package team_project.clat.exception;

public class MailVerifyException extends RuntimeException{
    public MailVerifyException() {
        super();
    }

    public MailVerifyException(String message) {
        super(message);
    }

    public MailVerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailVerifyException(Throwable cause) {
        super(cause);
    }

    protected MailVerifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
