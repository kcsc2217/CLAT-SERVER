package team_project.clat.domain.Dto.response;

import lombok.Data;

@Data
public class RestResponse {
    private String code;
    private String message;


    public RestResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
