package team_project.clat.dto;

import lombok.Data;

@Data
public class MessageMemoRequestDTO {

    private Long messageId;

    private Long chatRoomId;

    private String memo;

    public MessageMemoRequestDTO(Long messageId, Long chatRoomId, String memo) {
        this.messageId = messageId;
        this.chatRoomId = chatRoomId;
        this.memo = memo;
    }

    public MessageMemoRequestDTO() {
    }
}
