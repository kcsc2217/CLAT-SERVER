package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Message;

@Data
public class MemberAnswerResDTO {

    private Long messageId;

    private String answer;

    public MemberAnswerResDTO(Message message) {
        this.messageId = message.getId();
        this.answer = message.getAnswer().getAnswer();
    }

    public MemberAnswerResDTO() {
    }
}
