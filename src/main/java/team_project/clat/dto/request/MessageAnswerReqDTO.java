package team_project.clat.dto.request;

import lombok.Data;

@Data
public class MessageAnswerReqDTO {
    private Long messageId;

    private Long chatRoomId;

    private String answer;

    public MessageAnswerReqDTO(Long messageId, Long chatRoomId, String answer) {
        this.messageId = messageId;
        this.chatRoomId = chatRoomId;
        this.answer = answer;
    }

    public MessageAnswerReqDTO() {
    }
}