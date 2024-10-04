package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageFileResDTO {

    private Long messageId;

    private String senderName;


    private String message;

    private List<String> imageUrl;

    private LocalDateTime timestamp;

    private Long answerId;

    private String answer;

    private LikeResDTO likeResDTO;

    public MessageFileResDTO(Message message) {
        this.messageId = message.getId();

        if(message.getMember() == null){
            this.senderName = "";
        }
        else{
            this.senderName = message.getMember().getUsername();
        }
        this.imageUrl = message.getImages().stream().map(image -> image.getAccessUrl()).collect(Collectors.toList());
        this.timestamp = message.getCreatedDate();
        this.message = message.getMessage();
        if(message.getAnswer() == null){
            this.answer = "";
            this.answerId = null;
        }else{
            this.answerId = message.getAnswer().getId();
            this.answer = message.getAnswer().getAnswer();
        }

        likeResDTO = new LikeResDTO(message.getLikes());

    }


    public MessageFileResDTO() {
    }
}
