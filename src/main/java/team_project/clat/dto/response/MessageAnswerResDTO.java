package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Answer;

import java.time.LocalDateTime;

@Data
public class MessageAnswerResDTO {

    private Long answerId;

    private String answer;
    private Long messageId;

    private String senderName;

    private LocalDateTime timestamp;

    public MessageAnswerResDTO(Answer answer) {
        this.answerId = answer.getId();
        this.answer = answer.getAnswer();
        this.messageId = answer.getMessage().getId();
        this.timestamp = answer.getCreatedDate();
        this.senderName = answer.getMember().getName();
    }

    public MessageAnswerResDTO() {
    }
}
