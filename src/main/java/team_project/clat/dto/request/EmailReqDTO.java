package team_project.clat.dto.request;

import lombok.Getter;

public class EmailReqDTO {
    @Getter
    public static class EmailForVerificationRequest {
        private String email;
    }

    @Getter
    public static class VerificationCodeRequest {
        private String email;
        private String code;
    }
}
