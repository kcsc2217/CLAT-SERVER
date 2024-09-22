package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.ChatRoom;

import java.time.LocalDateTime;

@Data
public class ChatRoomInformationDTO {

    private String courseName;
    private String chatRoomName;
    private LocalDateTime creationTime;
    private int roomKey;
    private int week;


    public ChatRoomInformationDTO(ChatRoom chatRoom) {
        this.courseName = chatRoom.getCourse().getCourseName();
        this.chatRoomName = chatRoom.getRoomName();
        this.creationTime = chatRoom.getCreatedDate();
        this.roomKey = chatRoom.getRoomKey();
        this.week = chatRoom.getWeek();
    }

    public ChatRoomInformationDTO() {
    }
}
