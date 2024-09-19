package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.Enum.Emoticon;

@Data
public class LikeRequestDTO {

    private Long messageId;

    private Emoticon emoticon;


    public LikeRequestDTO(Long messageId, Emoticon emoticon) {
        this.messageId = messageId;
        this.emoticon = emoticon;
    }

    public LikeRequestDTO() {
    }

}
