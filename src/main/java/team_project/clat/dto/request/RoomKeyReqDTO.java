package team_project.clat.dto.request;

import lombok.Data;

@Data
public class RoomKeyReqDTO {

    private Long chatRoomId;

    private int roomKey;

    public RoomKeyReqDTO(Long chatRoomId, int roomKey) {
        this.chatRoomId = chatRoomId;
        this.roomKey = roomKey;
    }

    public RoomKeyReqDTO() {
    }
}
