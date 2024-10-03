package team_project.clat.dto.response;

import lombok.Data;

@Data
public class ChatRoomMemberResDTO {

    private boolean isPassWorldEnter;

    public ChatRoomMemberResDTO(boolean isPassWorldEnter) {
        this.isPassWorldEnter = isPassWorldEnter;
    }

    public ChatRoomMemberResDTO() {
    }
}
