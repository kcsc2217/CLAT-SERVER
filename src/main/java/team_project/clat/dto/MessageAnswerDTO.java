package team_project.clat.dto;

import lombok.Data;

@Data
public class MessageAnswerDTO {
    private Long messageId;

    private Long chatRoomId;

    private String answer;

    public MessageAnswerDTO(Long messageId, Long chatRoomId, String answer) {
        this.messageId = messageId;
        this.chatRoomId = chatRoomId;
        this.answer = answer;
    }

    public MessageAnswerDTO() {
    }
}
