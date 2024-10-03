package team_project.clat.dto.response;

import lombok.Data;

@Data
public class HomeChatRoomResDTO {

    private Long chatRoomId;

    private int week;

    public HomeChatRoomResDTO(Long id, int week) {
        this.chatRoomId = id;
        this.week = week;
    }

    public HomeChatRoomResDTO() {
    }
}
