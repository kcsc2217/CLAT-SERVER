package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.ChatRoom;

import java.util.List;
import java.util.stream.Collectors;

@Data

public class ChatRoomMessageDTO {
    private String courseName;

    private String roomName;

    private List<MessageIncludeFileDTO>  messageFileResponseDTOS;

    public ChatRoomMessageDTO(ChatRoom chatRoom) {
        this.courseName = chatRoom.getCourse().getCourseName();
        this.roomName = chatRoom.getRoomName();
        this.messageFileResponseDTOS = chatRoom.getMessageList().stream().map(MessageIncludeFileDTO::new).collect(Collectors.toList());
    }

    public ChatRoomMessageDTO() {
    }
}
