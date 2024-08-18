package team_project.clat.dto;

import lombok.Getter;
import lombok.Setter;
import team_project.clat.domain.Enum.UserType;

@Getter
@Setter
public class JoinDto {

    private String name;
    private String username;
    private String password;
    private String schoolName;
    private UserType userType;
}
