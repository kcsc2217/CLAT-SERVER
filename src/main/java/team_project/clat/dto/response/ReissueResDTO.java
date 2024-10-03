package team_project.clat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ReissueResDTO {
    CommonResultResDTO commonResultResDTO;
    String newAccess;
    String newRefresh;
    HttpStatus httpStatus;
}
