package team_project.clat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JoinResDTO {
    JoinResultResDTO joinResultResDTO;
    String access;
    String refresh;
}
