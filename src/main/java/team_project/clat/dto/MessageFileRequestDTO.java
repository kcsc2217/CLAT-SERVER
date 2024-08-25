package team_project.clat.dto;

import lombok.Data;

@Data
public class MessageFileRequestDTO {

    private Long courseId;

    private String senderName;

    private String imageUrl;

    public MessageFileRequestDTO(Long courseId, String senderName, String imageUrl) {
        this.courseId = courseId;
        this.senderName = senderName;
        imageUrl = imageUrl;
    }

    public MessageFileRequestDTO() {
    }
}
