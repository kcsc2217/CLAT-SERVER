package team_project.clat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.cglib.core.Local;
import team_project.clat.domain.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MessageResponse {

    private Long messageId;

    private String senderName;


    private String message;

    private LocalDateTime timestamp;


    public MessageResponse(Message message) {
        this.messageId = message.getId();
        this.senderName = message.getMember().getUsername();
        this.message = message.getMessage();
        this.timestamp = message.getCreatedDate();
    }

    public MessageResponse(String senderName, String message, LocalDateTime timestamp) {
        this.senderName = senderName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageResponse() {
    }
}
