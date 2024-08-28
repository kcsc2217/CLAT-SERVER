package team_project.clat.dto;

import lombok.Data;

@Data
public class RoomKeyRes {

    private boolean flag;

    public RoomKeyRes(boolean flag) {
        this.flag = flag;
    }

    public RoomKeyRes() {
    }
}
