package team_project.clat.config.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import team_project.clat.exception.AccessTokenInvalidException;
import team_project.clat.jwt.JwtUtil;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            if (!this.validateAccessToken(accessor.getFirstNativeHeader("access"))) {
                throw new AccessTokenInvalidException("access token이 필요합니다.");
            }

            if(!isValidationToken(accessor.getFirstNativeHeader("access"))){
                throw new AccessTokenInvalidException("access token이 만료되었습니다.");
            }

        }

        return message;
    }

    private boolean validateAccessToken(String accessToken) {
        if(accessToken == null ){  //토큰값이 없으면 return false;
            return false;
        }
        return true;
    }


    private boolean isValidationToken(String token){
        return jwtUtil.isExpired(token);
    }

}
