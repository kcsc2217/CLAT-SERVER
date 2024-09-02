package team_project.clat.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageFileRequestDTO {

    private Long chatRoomId;

    private List<FileImageDTO> fileImageDTOList;



    public MessageFileRequestDTO(Long chatRoomId, List<FileImageDTO> fileImageDTOList) {
        this.chatRoomId = chatRoomId;
        this.fileImageDTOList = fileImageDTOList;
    }

    public MessageFileRequestDTO() {
    }
}
