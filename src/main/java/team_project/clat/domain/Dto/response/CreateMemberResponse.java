package team_project.clat.domain.Dto.response;

import lombok.Data;

@Data
public class CreateMemberResponse {
    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
