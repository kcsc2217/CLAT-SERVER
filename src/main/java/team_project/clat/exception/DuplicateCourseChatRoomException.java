package team_project.clat.exception;

public class DuplicateCourseChatRoomException extends RuntimeException {
    public DuplicateCourseChatRoomException() {
    }

    public DuplicateCourseChatRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCourseChatRoomException(Throwable cause) {
        super(cause);
    }

    public DuplicateCourseChatRoomException(String message) {
        super(message);
    }
}
