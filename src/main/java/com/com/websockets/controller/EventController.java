package com.com.websockets.controller;

import com.com.websockets.api.SocketMessage;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class EventController {

    private static final DateFormatter formatter = new DateFormatter(new SimpleDateFormat("HH:mm"));

    private static final Random rng = new Random();

    private final String[] messagePool = {
            "Things dun happen'd",
            "Oh no, things got bork'd",
            "Good news everyone",
            "Are you still there?"
    };

    private final SimpMessagingTemplate template;

    public EventController(final SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat")
    public void send(SocketMessage message) {
        System.out.println(message.getMessage());
        final Map<String, String> theMaptoSend = new HashMap<>();
        theMaptoSend.put("message", message.getMessage());
        theMaptoSend.put("to", message.getTo());
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            theMaptoSend.put("timestamp", this.formatter.valueToString(new Date()));
        } catch (ParseException ex) {
            System.out.println("darn");
        }
        this.template.convertAndSend("/topic/" + message.getTo(),
                                     new GenericMessage<>(theMaptoSend, new MessageHeaders(null)));
    }


    @Scheduled(fixedDelay = 50_000L)
    public void doSendPeriodically() {
        System.out.println("Periodically sending the thing \\o/");
        final Map<String, String> theMaptoSend = new HashMap<>();
        theMaptoSend.put("message", this.messagePool[rng.nextInt(this.messagePool.length)]);
        theMaptoSend.put("to", "world");
        try {
            theMaptoSend.put("timestamp", this.formatter.valueToString(new Date()));
        } catch (ParseException e) {
            System.out.println("Darm");
        }
        this.template.convertAndSend("/topic/Everyone",
                                     new GenericMessage<>(theMaptoSend, new MessageHeaders(null)));
    }
}
