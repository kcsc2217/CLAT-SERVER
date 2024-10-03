package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.ChatRoom;

import java.time.LocalDateTime;

@Data
public class ChatRoomInformationResDTO {

    private String courseName;
    private String chatRoomName;
    private LocalDateTime creationTime;
    private int roomKey;
    private int week;


    public ChatRoomInformationResDTO(ChatRoom chatRoom) {
        this.courseName = chatRoom.getCourse().getCourseName();
        this.chatRoomName = chatRoom.getRoomName();
        this.creationTime = chatRoom.getCreatedDate();
        this.roomKey = chatRoom.getRoomKey();
        this.week = chatRoom.getWeek();
    }

    public ChatRoomInformationResDTO() {
    }
}
