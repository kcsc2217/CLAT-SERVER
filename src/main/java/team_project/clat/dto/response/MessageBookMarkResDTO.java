package team_project.clat.dto.response;

import lombok.Getter;

@Getter
public class MessageBookMarkResDTO {

    private Long messageId;

   private Long bookMarkId;

    public MessageBookMarkResDTO(Long bookMarkId, Long messageId) {
        this.bookMarkId = bookMarkId;
        this.messageId = messageId;
    }
}
