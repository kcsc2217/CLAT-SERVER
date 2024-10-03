package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Message;

@Data
public class MemberMessageResDTO {

    private Long message_id;
    private String message_content;

    public MemberMessageResDTO(Message message) {
        this.message_id = message.getId();
        this.message_content = message.getMessage();
    }

    public MemberMessageResDTO() {
    }
}
