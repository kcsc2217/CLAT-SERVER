package team_project.clat.config.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import team_project.clat.exception.AccessTokenInvalidException;
import team_project.clat.jwt.JwtUtil;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class StompHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    String username;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            String token = accessor.getFirstNativeHeader("Authorization");

            if (token == null || token.isEmpty()) {
                throw new AccessTokenInvalidException("Access token is required.");
            }

            if (jwtUtil.isExpired(token)) {
                throw new AccessTokenInvalidException("Access token has expired.");
            }

             username = jwtUtil.getUsername(token);
            accessor.addNativeHeader("username", username);
            accessor.getSessionAttributes().put("username", username);
            // Optional: Add logging for debugging
            System.out.println("Valid token received: " + token);
        }

        return message;
    }

//    @EventListener(SessionConnectEvent.class)
//    public void onApplicationEvent(SessionConnectEvent event) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
//            accessor.getSessionAttributes().put("username", username);
//
//    }

}
