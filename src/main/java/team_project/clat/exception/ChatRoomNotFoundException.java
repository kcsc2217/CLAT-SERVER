package team_project.clat.exception;

public class ChatRoomNotFoundException extends RuntimeException{
    public ChatRoomNotFoundException() {
    }

    public ChatRoomNotFoundException(String message) {
        super(message);
    }

    public ChatRoomNotFoundException(Throwable cause) {
        super(cause);
    }

    public ChatRoomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
