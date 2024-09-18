package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.Message;

@Data
public class MemoResponseDTO {
    private Long messageId;

    private Long memoId;

    private String memo;

    public MemoResponseDTO(Message message) {
        this.messageId = message.getId();
        if(message.getMemo() != null) {
            this.memoId = message.getMemo().getId();
            this.memo = message.getMemo().getMemo();
        }else{
            this.memoId = null;
            this.memo = null;
        }

    }

    public MemoResponseDTO() {
    }
}
