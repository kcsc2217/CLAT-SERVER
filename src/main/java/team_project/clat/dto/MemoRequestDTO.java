package team_project.clat.dto;

import lombok.Data;

@Data
public class MemoRequestDTO {

    private Long messageId;

    private String memoContent;

    public MemoRequestDTO(Long messageId, String memoContent) {
        this.messageId = messageId;
        this.memoContent = memoContent;
    }

    public MemoRequestDTO() {
    }
}
