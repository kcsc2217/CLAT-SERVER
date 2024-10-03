package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Enum.Emoticon;
import team_project.clat.domain.Like;

import java.util.List;

@Data
public class LikeResDTO {

    private int heartCount = 0;
    private int sadCount = 0;
    private int likeCount = 0;


    public LikeResDTO(List<Like> likeList) {
        emotionCount(likeList);
    }

    public LikeResDTO() {
    }

    private void emotionCount(List<Like> likeList) {
        for(Like like : likeList) {
            if(like.getEmoticon() == Emoticon.HEART) {
                heartCount++;
            }
            else if(like.getEmoticon() == Emoticon.SAD) {
                sadCount++;
            }
            else if(like.getEmoticon() == Emoticon.LIKE){
                likeCount++;
            }
        }
    }

}
