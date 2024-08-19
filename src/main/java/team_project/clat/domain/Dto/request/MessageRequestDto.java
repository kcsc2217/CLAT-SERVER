package team_project.clat.domain.Dto.request;

import lombok.Data;

@Data
public class MessageRequestDto {

    private Long courseId;
    private String message;

    private String senderName;

    public MessageRequestDto(Long courseId, String message, String senderName) {
        this.courseId = courseId;
        this.message = message;
        this.senderName = senderName;
    }

    public MessageRequestDto() {
    }
}
