package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;

@Data
public class MessageResDTO {

    private Long messageId;

    private String senderName;


    private String message;

    private LocalDateTime timestamp;


    public MessageResDTO(Message message) {
        this.messageId = message.getId();
        this.senderName = message.getMember().getUsername();
        this.message = message.getMessage();
        this.timestamp = message.getCreatedDate();
    }

    public MessageResDTO(String senderName, String message, LocalDateTime timestamp) {
        this.senderName = senderName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageResDTO() {
    }
}
