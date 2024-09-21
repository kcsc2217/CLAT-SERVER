package team_project.clat.config.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;



    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message); // 메세지 헤더에 접근이 가능함

        log.info("메시지 연결");

        if (accessor.getCommand() == StompCommand.CONNECT) {
            String token = accessor.getFirstNativeHeader("Authorization"); // http에서는 custom 헤더를 사요하고 있지만 stomp는 웹소켓 환경에서 동작하므로 해당 헤더를 알지 못함

            if (token == null || token.isEmpty()) {
                throw new AccessTokenInvalidException("Access token is required.");
            }

            if (jwtUtil.isExpired(token)) {
                throw new AccessTokenInvalidException("Access token has expired.");
            }

          String username = jwtUtil.getUsername(token);
            accessor.getSessionAttributes().put("username", username);
            // Optional: Add logging for debugging
            System.out.println("Valid token received: " + token);
        }

        return message;
    }
//
//    @EventListener(SessionConnectEvent.class)
//    public void onApplicationEvent(SessionConnectEvent event) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
//            accessor.getSessionAttributes().put("username", username);
//
//    }

}
