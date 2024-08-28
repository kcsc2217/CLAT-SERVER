package team_project.clat.dto;

import lombok.Data;

@Data
public class RoomKeyReq {

    private Long chatRoomId;

    private int roomKey;

    public RoomKeyReq(Long chatRoomId, int roomKey) {
        this.chatRoomId = chatRoomId;
        this.roomKey = roomKey;
    }

    public RoomKeyReq() {
    }
}
