package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.ChatRoom;

import java.util.List;
import java.util.stream.Collectors;

@Data

public class ChatRoomMessageResDTO {

    private String roomName;

    private List<MessageListResDTO>  messageFileResponseDTOS;

    public ChatRoomMessageResDTO(ChatRoom chatRoom) {
        this.roomName = chatRoom.getRoomName();
        this.messageFileResponseDTOS = chatRoom.getMessageList().stream().map(MessageListResDTO::new).collect(Collectors.toList());
    }

    public ChatRoomMessageResDTO(String roomName) {
        this.roomName = roomName;
    }



    public ChatRoomMessageResDTO() {
    }
}
