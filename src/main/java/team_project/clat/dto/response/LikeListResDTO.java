package team_project.clat.dto.response;


import lombok.Getter;

import java.util.List;

@Getter
public class LikeListResDTO {
    private List<LikeResDTO> likes;

    public LikeListResDTO(List<LikeResDTO> likes) {
        this.likes = likes;
    }
}
