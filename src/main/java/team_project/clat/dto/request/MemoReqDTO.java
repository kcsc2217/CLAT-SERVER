package team_project.clat.dto.request;

import lombok.Data;

@Data
public class MemoReqDTO {

    private Long messageId;

    private String memoContent;

    public MemoReqDTO(Long messageId, String memoContent) {
        this.messageId = messageId;
        this.memoContent = memoContent;
    }

    public MemoReqDTO() {
    }
}
