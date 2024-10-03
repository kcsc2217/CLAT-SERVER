package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;

@Data
public class MessageMemoResDTO {

    private Long memoId;

    private String memo;
    private Long messageId;

    private String senderName;

    private LocalDateTime timestamp;

    public MessageMemoResDTO(Message message) {
        this.memoId = message.getMemo().getId();
        this.memo = message.getMemo().getMemoContent();
        this.messageId = message.getId();
        this.senderName = message.getMember().getName();
        this.timestamp = message.getCreatedDate();
    }

    public MessageMemoResDTO() {
    }
}
