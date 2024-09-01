package team_project.clat.domain.Dto.request;

import lombok.Data;

@Data
public class MessageRequestDto {

    private Long chatRoomId;
    private String message;


    public MessageRequestDto(Long chatRoomId, String message) {
        this.chatRoomId = chatRoomId;
        this.message = message;
    }

    public MessageRequestDto() {
    }
}
