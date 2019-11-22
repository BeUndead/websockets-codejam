package com.com.websockets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/user/queue/specific-user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .addInterceptors(new HandshakeInterceptor() {
                    @Override
                    public boolean beforeHandshake(final ServerHttpRequest request,
                                                   final ServerHttpResponse response,
                                                   final WebSocketHandler wsHandler,
                                                   final Map<String, Object> attributes) {
                        if (request instanceof ServletServerHttpRequest) {
                            ServletServerHttpRequest servletRequest
                                    = (ServletServerHttpRequest) request;
                            HttpSession session = servletRequest
                                    .getServletRequest().getSession();
                            attributes.put("sessionId", session.getId());
                            System.out.println("SessionId: " + session.getId());
                        }
                        return true;
                    }

                    @Override
                    public void afterHandshake(final ServerHttpRequest request,
                                               final ServerHttpResponse response,
                                               final WebSocketHandler wsHandler,
                                               final Exception exception) {

                    }
                });
    }
}
