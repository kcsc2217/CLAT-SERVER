package team_project.clat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@RedisHash("token")
@AllArgsConstructor
@Getter
public class Token {

    @Id
    private String id;
    private String refreshToken;
    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long expiration;
}
