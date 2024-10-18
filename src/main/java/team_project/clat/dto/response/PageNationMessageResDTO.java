package team_project.clat.dto.response;


import lombok.Getter;
import team_project.clat.domain.Answer;
import team_project.clat.domain.Image;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter

public class PageNationMessageResDTO {

    private Long messageId;

    private String senderName;


    private String message;

    private List<String> imageUrl;

    private LocalDateTime timestamp;

    private Long answerId;

    private String answer;

    private LikeResDTO likeResDTO;

    public PageNationMessageResDTO(Message message) {
        this.messageId = message.getId();
        this.senderName = message.getMember().getName();
        this.message = message.getMessage();
        this.imageUrl = message.getImages()
                                    .stream().map(Image::getAccessUrl).collect(Collectors.toList());
        this.timestamp = message.getCreatedDate();
        this.answerId = Optional.ofNullable(message.getAnswer())
                        .map(Answer::getId)
                        .orElse(null);
        this.answer =  Optional.ofNullable(message.getAnswer())
                        .map(Answer::getAnswer)
                        .orElse(null);
        this.likeResDTO =  new LikeResDTO(message.getLikes());
    }
}
