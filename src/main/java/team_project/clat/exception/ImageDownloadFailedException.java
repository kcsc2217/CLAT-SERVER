package team_project.clat.exception;

public class ImageDownloadFailedException extends RuntimeException{

    public ImageDownloadFailedException() {
        super();
    }

    public ImageDownloadFailedException(String message) {
        super(message);
    }

    public ImageDownloadFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageDownloadFailedException(Throwable cause) {
        super(cause);
    }

    protected ImageDownloadFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
