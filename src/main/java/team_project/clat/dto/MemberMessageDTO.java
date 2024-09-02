package team_project.clat.dto;

import lombok.Data;

@Data
public class MemberMessageDTO {

    private Long member_id;
    private String message_content;

    public MemberMessageDTO(Long member_id, String message_content) {
        this.member_id = member_id;
        this.message_content = message_content;
    }

    public MemberMessageDTO() {
    }
}
