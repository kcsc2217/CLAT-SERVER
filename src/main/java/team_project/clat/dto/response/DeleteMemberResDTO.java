package team_project.clat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class DeleteMemberResDTO {
    CommonResultResDTO commonResultResDTO;
    String newAccess;
    String newRefresh;
    HttpStatus httpStatus;
}
