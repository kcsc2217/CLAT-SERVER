package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;

@Data
public class MessageFileResponseDTO {

    private Long messageId;

    private String senderName;


    private String imageUrl;

    private LocalDateTime timestamp;

    public MessageFileResponseDTO(Message message) {
        this.messageId = message.getId();
        this.senderName = message.getSenderName();
        this.imageUrl = message.getImageUrl();
        this.timestamp = message.getCreatedDate();
    }

    public MessageFileResponseDTO() {
    }
}
