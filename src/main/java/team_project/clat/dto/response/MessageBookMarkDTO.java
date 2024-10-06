package team_project.clat.dto.response;

import lombok.Getter;
import team_project.clat.domain.BookMark;

@Getter
public class MessageBookMarkDTO {

    private Long bookmarkId;

    private String messageContent;


    public MessageBookMarkDTO(BookMark bookmark) {
        this.bookmarkId = bookmark.getId();
        this.messageContent = bookmark.getMessage().getMessage();
    }

}
