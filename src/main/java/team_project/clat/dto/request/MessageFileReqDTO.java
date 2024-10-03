package team_project.clat.dto.request;

import lombok.Data;
import team_project.clat.dto.response.FileImageResDTO;

import java.util.List;

@Data
public class MessageFileReqDTO {

    private Long chatRoomId;

    private List<FileImageResDTO> fileImageDTOList;



    public MessageFileReqDTO(Long chatRoomId, List<FileImageResDTO> fileImageDTOList) {
        this.chatRoomId = chatRoomId;
        this.fileImageDTOList = fileImageDTOList;
    }

    public MessageFileReqDTO() {
    }
}
