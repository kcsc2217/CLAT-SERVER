package team_project.clat.dto.request;

import lombok.Data;

@Data
public class MessageMemoReqDTO {

    private Long messageId;


    private String memo;

    public MessageMemoReqDTO(Long messageId, String memo) {
        this.messageId = messageId;
        this.memo = memo;
    }

    public MessageMemoReqDTO() {
    }
}
