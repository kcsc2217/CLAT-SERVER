package team_project.clat.dto;

import lombok.Data;
import team_project.clat.domain.Memo;

@Data
public class MemoUpdateDTO {


    private Long memoId;

    private String updateMemoContent;

    public MemoUpdateDTO(Memo memo) {
        this.memoId = memo.getId();
        this.updateMemoContent = memo.getMemoContent();

    }

    public MemoUpdateDTO() {

    }
}
