package team_project.clat.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import team_project.clat.domain.Enum.Emoticon;
import team_project.clat.domain.Like;

import java.util.HashMap;
import java.util.List;

@Data
public class LikeResDTO {

    private Long messageId;

    private Emoticon emoticon;

    private Long count;



    @QueryProjection
    public LikeResDTO(Emoticon emoticon, Long count, Long messageId) {
        this.messageId = messageId;
        this.emoticon = emoticon;
        this.count = count;
    }

    public LikeResDTO() {
    }


}
