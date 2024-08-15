package team_project.clat.domain.Dto.response;

import lombok.Data;

@Data
public class CreateMemberResponse {
    private Long chat_Room_id;

    public CreateMemberResponse(Long id) {
        this.chat_Room_id = id;
    }
}
