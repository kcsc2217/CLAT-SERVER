package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MessageFileIncludedRespDTO {

    private Long messageId;

    private String senderName;


    private List<String> imageUrl = new ArrayList<>();

    private LocalDateTime timestamp;

    public MessageFileIncludedRespDTO(Message message, List<FileImageResDTO> fileImageDTOList) {
        this.messageId = message.getId();
        this.senderName = message.getMember().getUsername();
        for(FileImageResDTO fileImageDTO : fileImageDTOList){
            this.imageUrl.add(fileImageDTO.getImageURL());
        }
        this.timestamp = message.getCreatedDate();
    }

    public MessageFileIncludedRespDTO() {
    }
}
