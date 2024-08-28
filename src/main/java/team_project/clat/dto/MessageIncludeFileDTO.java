package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageIncludeFileDTO {

    private Long messageId;

    private String senderName;


    private String message;

    private List<String> imageUrl;

    private LocalDateTime timestamp;

    public MessageIncludeFileDTO(Message message) {
        this.messageId = message.getId();
        this.senderName = message.getSenderName();
        this.imageUrl = message.getImages().stream().map(image -> image.getAccessUrl()).collect(Collectors.toList());
        this.timestamp = message.getCreatedDate();
        this.message = message.getMessage();
    }

    public MessageIncludeFileDTO() {
    }
}
