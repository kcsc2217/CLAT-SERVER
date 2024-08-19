package team_project.clat.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
public class VerificationCode {
    private String code;
    private LocalDateTime createAt;
    private final Integer expirationTimeInMinutes=5;


    public boolean isExpired(LocalDateTime verifiedAt) {
        LocalDateTime expiredAt = createAt.plusMinutes(expirationTimeInMinutes);
        return verifiedAt.isAfter(expiredAt);
    }

    public String generateCodeMessage() {
        Random random = new Random();
        int min = 100000;  // 최소 6자리 숫자
        int max = 999999;  // 최대 6자리 숫자
        int randomNumber = random.nextInt(max - min + 1) + min;

        this.code = String.valueOf(randomNumber);
        this.createAt = LocalDateTime.now();

        return String.valueOf(randomNumber);
    }
}
