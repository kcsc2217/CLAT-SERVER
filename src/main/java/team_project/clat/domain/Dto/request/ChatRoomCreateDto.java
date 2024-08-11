package team_project.clat.domain.Dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ChatRoomCreateDto {

    @NotEmpty
    private String roomName;

    @NotEmpty
    private Long chatRoomId;

    public ChatRoomCreateDto(String roomName, Long chatRoomId) {
        this.roomName = roomName;
        this.chatRoomId = chatRoomId;
    }

    public ChatRoomCreateDto() {
    }
}
