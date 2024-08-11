package team_project.clat.domain.Dto.request;

import lombok.Data;

@Data
public class MessageRequestDto {

    private Long chatRoomId;
    private String message;

    private String senderName;

    public MessageRequestDto(Long chatRoomId, String message, String senderName) {
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.senderName = senderName;
    }

    public MessageRequestDto() {
    }
}
