package team_project.clat.dto;

import lombok.Data;

@Data
public class ChatRoomMemberDTO {

    private boolean isPassWorldEnter;

    public ChatRoomMemberDTO(boolean isPassWorldEnter) {
        this.isPassWorldEnter = isPassWorldEnter;
    }

    public ChatRoomMemberDTO() {
    }
}
