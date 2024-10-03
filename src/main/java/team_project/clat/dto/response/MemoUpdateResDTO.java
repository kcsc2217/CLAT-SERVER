package team_project.clat.dto.response;

import lombok.Data;
import team_project.clat.domain.Memo;

@Data
public class MemoUpdateResDTO {

    private Long memoId;

    private String updateMemoContent;

    public MemoUpdateResDTO(Memo memo) {
        this.memoId = memo.getId();
        this.updateMemoContent = memo.getMemoContent();
    }




}





