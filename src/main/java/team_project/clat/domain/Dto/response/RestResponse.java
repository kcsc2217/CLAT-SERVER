package team_project.clat.domain.Dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RestResponse {
    @Schema(description = "error Code", example = "error Code")
    private String code;
    @Schema(description = "에러 메세지", example = "error Message")
    private String message;


    public RestResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
