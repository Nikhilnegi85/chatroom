package com.learning.websocket.config;

import com.learning.websocket.chat.ChatMessage;
import com.learning.websocket.enums.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (Objects.nonNull(username)) {
            log.info("user disconnected : {}", username);
            ChatMessage chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();

            messageSendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}
