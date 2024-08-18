package team_project.clat.domain.Dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MessageResponse {
    @Schema(description = "senderName", example = "사용자 이름")
    private String senderName;

    @Schema(description = "message", example = "메세지 내용")
    private String message;
    @Schema(description = "timestamp", example = "메세지 저장 시간")
    private LocalDateTime timestamp;

    public MessageResponse(String senderName, String message, LocalDateTime timestamp) {
        this.senderName = senderName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public MessageResponse() {
    }
}
