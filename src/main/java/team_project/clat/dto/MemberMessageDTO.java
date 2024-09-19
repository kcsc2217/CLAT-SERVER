package team_project.clat.dto;

import lombok.Data;

@Data
public class MemberMessageDTO {

    private Long message_id;
    private String message_content;

    public MemberMessageDTO(Long message_id, String message_content) {
        this.message_id = message_id;
        this.message_content = message_content;
    }

    public MemberMessageDTO() {
    }
}
