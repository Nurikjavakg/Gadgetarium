package peaksoft.configWebSocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import peaksoft.dto.user.ChatMessage;
import peaksoft.enums.Action;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    public final SimpMessageSendingOperations messageTemplate;
//
//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        // TODO -- to be implemented
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        System.out.println(headerAccessor.getSessionAttributes());
//        if (username != null) {
//            log.info("user disconnected {}", username);
//            var chatMessage = ChatMessage.builder()
//                    .type(Action.LEAVE)
//                    .sender(username)
//                    .build();
//            messageTemplate.convertAndSend("/topic/public", chatMessage);
//        }
//    }
}
