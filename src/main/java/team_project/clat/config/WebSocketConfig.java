package team_project.clat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // 클라이언트가 엔드포인트로 설정한 /ws로 WebSocket 연결 시도
                .setAllowedOriginPatterns("*").withSockJS();// 모든 도메인에서 접근이 가능하도록 함 그리고 Socket Js 즉 웹 소켓
        // 을 지원하지 않는 브라우저에서도 HTTP 풀링 방식으로 WebSocket과 유사하게 동작 할 수 있다.
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub"); // /sub/~ 로 구독한 클라이언트에게 서버가 메세지를 보낼 수 있다. 예를 들어 클라리언트가 /sub/chat를 구독을 하고 있다면
        // 서버는 /sub/chat 경로로 메세지를 보낼 수 있다.  메세지를 받을떄

        registry.setApplicationDestinationPrefixes("/pub"); // 클라이언트가 서버로 메세지를 보낼때 사용하는 경로 메세지를 보낼때

    }


}
