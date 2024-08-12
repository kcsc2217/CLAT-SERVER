package team_project.clat.domain.Dto.response;

import lombok.Data;

@Data
public class MessageResponse {
    private String senderName;
    private String message;
    private String timestamp;

    public MessageResponse(String senderName, String message, String timestamp) {
        this.senderName = senderName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageResponse() {
    }
}
