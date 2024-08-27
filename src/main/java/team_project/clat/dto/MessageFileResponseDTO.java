package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.Message;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageFileResponseDTO {

    private Long messageId;

    private String senderName;


    private List<String> imageUrl;

    private LocalDateTime timestamp;

    public MessageFileResponseDTO(Message message, List<FileImageDTO> fileImageDTOList) {
        this.messageId = message.getId();
        this.senderName = message.getSenderName();
        for(FileImageDTO fileImageDTO : fileImageDTOList){
            this.imageUrl.add(fileImageDTO.getImageURL());
        }
        this.timestamp = message.getCreatedDate();
    }

    public MessageFileResponseDTO() {
    }
}