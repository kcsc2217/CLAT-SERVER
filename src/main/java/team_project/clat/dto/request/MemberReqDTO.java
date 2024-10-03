package team_project.clat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberReqDTO {

    private String name;
    private String username;
    private String schoolName;
    private String UserType;

}
