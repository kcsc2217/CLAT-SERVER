package team_project.clat.dto;

import lombok.Data;

@Data
public class MemberAnswerDTO {

    private Long messageId;

    private String answer;

    public MemberAnswerDTO(Long messageId, String answer) {
        this.messageId = messageId;
        this.answer = answer;
    }

    public MemberAnswerDTO() {
    }
}
