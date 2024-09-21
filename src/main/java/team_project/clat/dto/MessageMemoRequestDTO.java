package team_project.clat.dto;

import lombok.Data;

@Data
public class MessageMemoRequestDTO {

    private Long messageId;


    private String memo;

    public MessageMemoRequestDTO(Long messageId,  String memo) {
        this.messageId = messageId;
        this.memo = memo;
    }

    public MessageMemoRequestDTO() {
    }
}
