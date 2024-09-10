package team_project.clat.dto;

import lombok.Data;

@Data
public class HomeChatRoomDTO {

    private Long chatRoomId;

    private int week;

    public HomeChatRoomDTO(Long id, int week) {
        this.chatRoomId = id;
        this.week = week;
    }

    public HomeChatRoomDTO() {
    }
}
