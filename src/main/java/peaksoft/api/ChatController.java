package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import peaksoft.dto.user.ChatMessage;
import peaksoft.entities.User;
import peaksoft.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {
    private UserRepository userRepository;




    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor accessor) {
        User user = userRepository.findByEmail(chatMessage.getSender());

        if (user != null) {
            accessor.getSessionAttributes().put("username", user.getUsername());
            accessor.getSessionAttributes().put("userId", user.getId());
            chatMessage.setId(user.getId());
            return chatMessage;
        } else {
            // Handle the case where the user is not found more gracefully,
            // e.g., by returning an error message or logging the issue.
            throw new RuntimeException("User not found in the database");
        }
    }












//    private final SimpMessagingTemplate messagingTemplate;
//    private final ChatMessageService chatMessageService;
//
//    @MessageMapping("/chat")
//    public void processMessage(@Payload ChatMessage chatMessage) {
//        ChatMessage savedMsg = chatMessageService.save(chatMessage);
//        messagingTemplate.convertAndSendToUser(
//                chatMessage.getRecipientId(), "/queue/messages",
//                new ChatNotification(
//                        savedMsg.getId(),
//                        savedMsg.getSenderId(),
//                        savedMsg.getRecipientId(),
//                        savedMsg.getContent()
//                )
//        );
//    }
//
//    @GetMapping("/messages/{senderId}/{recipientId}")
//    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
//                                                              @PathVariable String recipientId) {
//        return ResponseEntity
//                .ok(chatMessageService.findChatMessages(senderId, recipientId));
//    }
}
