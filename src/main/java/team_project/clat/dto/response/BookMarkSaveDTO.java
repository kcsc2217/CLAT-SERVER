package team_project.clat.dto.response;

import lombok.Getter;

@Getter
public class BookMarkSaveDTO {

    private Long messageId;

   private Long bookMarkId;

    public BookMarkSaveDTO(Long bookMarkId, Long messageId) {
        this.bookMarkId = bookMarkId;
        this.messageId = messageId;
    }
}
