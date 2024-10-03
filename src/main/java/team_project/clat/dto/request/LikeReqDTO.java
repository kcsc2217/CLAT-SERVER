package team_project.clat.dto.request;

import lombok.Data;
import team_project.clat.domain.Enum.Emoticon;

@Data
public class LikeReqDTO {

    private Long messageId;

    private Emoticon emoticon;


    public LikeReqDTO(Long messageId, Emoticon emoticon) {
        this.messageId = messageId;
        this.emoticon = emoticon;
    }

    public LikeReqDTO() {
    }

}
