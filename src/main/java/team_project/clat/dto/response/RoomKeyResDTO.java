package team_project.clat.dto.response;

import lombok.Data;

@Data
public class RoomKeyResDTO {

    private boolean flag;

    public RoomKeyResDTO(boolean flag) {
        this.flag = flag;
    }

    public RoomKeyResDTO() {
    }
}
