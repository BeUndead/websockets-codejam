package com.com.websockets.config;

import com.com.websockets.controller.TextHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class OurWebSocketConfigurer implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        registry.addHandler(new TextHandler(), "/hello");
    }
}
