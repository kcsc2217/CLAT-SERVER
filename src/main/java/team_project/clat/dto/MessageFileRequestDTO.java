package team_project.clat.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageFileRequestDTO {

    private Long courseId;

    private String senderName;

    private List<FileImageDTO> fileImageDTOList;



    public MessageFileRequestDTO(Long courseId, String senderName, List<FileImageDTO> fileImageDTOList) {
        this.courseId = courseId;
        this.senderName = senderName;
        this.fileImageDTOList = fileImageDTOList;
    }

    public MessageFileRequestDTO() {
    }
}
