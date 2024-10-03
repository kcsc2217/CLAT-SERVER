package team_project.clat.dto.request;

import lombok.Data;
import team_project.clat.domain.Memo;

@Data
public class MessageReqDTO {

    private Long chatRoomId;
    private String message;


    public MessageReqDTO(Long chatRoomId, String message) {
        this.chatRoomId = chatRoomId;
        this.message = message;
    }

    public MessageReqDTO() {
    }

 }


