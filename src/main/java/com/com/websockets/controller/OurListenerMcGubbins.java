package com.com.websockets.controller;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class OurListenerMcGubbins {

    @EventListener(SessionDisconnectEvent.class)
    public void handleDisconnect(final SessionDisconnectEvent event) {
        System.out.println(event.getSessionId());
    }

    @EventListener(SessionConnectedEvent.class)
    public void handleConnect(final SessionConnectedEvent event) {
        System.out.println(event.getSource());
    }
}
