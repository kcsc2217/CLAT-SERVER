package team_project.clat.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageFileRequestDTO {

    private Long courseId;

    private List<FileImageDTO> fileImageDTOList;



    public MessageFileRequestDTO(Long courseId, List<FileImageDTO> fileImageDTOList) {
        this.courseId = courseId;
        this.fileImageDTOList = fileImageDTOList;
    }

    public MessageFileRequestDTO() {
    }
}
