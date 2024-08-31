package team_project.clat.domain.Dto.request;

import lombok.Data;

@Data
public class MessageRequestDto {

    private Long courseId;
    private String message;



    public MessageRequestDto(Long courseId, String message) {
        this.courseId = courseId;
        this.message = message;
    }

    public MessageRequestDto() {
    }
}
