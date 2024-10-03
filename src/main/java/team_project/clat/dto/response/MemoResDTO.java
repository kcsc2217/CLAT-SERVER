package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Message;

@Data
public class MemoResDTO {
    private Long messageId;

    private Long memoId;

    private String memo;

    public MemoResDTO(Message message) {
        this.messageId = message.getId();
        if(message.getMemo() != null) {
            this.memoId = message.getMemo().getId();
            this.memo = message.getMemo().getMemoContent();
        }else{
            this.memoId = null;
            this.memo = null;
        }

    }

    public MemoResDTO() {
    }
}
