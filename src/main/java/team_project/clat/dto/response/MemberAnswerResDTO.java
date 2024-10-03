package team_project.clat.dto.response;

import lombok.Data;

@Data
public class MemberAnswerResDTO {

    private Long messageId;

    private String answer;

    public MemberAnswerResDTO(Long messageId, String answer) {
        this.messageId = messageId;
        this.answer = answer;
    }

    public MemberAnswerResDTO() {
    }
}
