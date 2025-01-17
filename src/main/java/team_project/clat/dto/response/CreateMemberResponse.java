package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.ChatRoom;

@Data
public class CreateMemberResponse {
    private Long chat_Room_id;
    private String chat_room_name;
    private int week;
    private int chat_room_key;



    public CreateMemberResponse(ChatRoom chatRoom) {
        this.chat_Room_id = chatRoom.getId();
        this.week = chatRoom.getWeek();
        this.chat_room_name = chatRoom.getRoomName();
        this.chat_room_key = chatRoom.getRoomKey();
    }

    public CreateMemberResponse() {
    }
}
