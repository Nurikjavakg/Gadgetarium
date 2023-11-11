package peaksoft.api;


import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import peaksoft.configWebSocket.Message;
import peaksoft.entities.User;
import peaksoft.enums.Action;
import peaksoft.services.servicesImpl.MemberStore;


@Controller
@Tag(name = "Chat API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ChatApi {


    private final MemberStore memberStore;
    private final SimpMessagingTemplate simpMessagingTemplate;



    @MessageMapping("/user")
    public void getusers(User user, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        User newUser = new User(user.getId(), null, user.getUsername());
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("user", newUser);
        memberStore.addMember(newUser);
        sendMembersList();
        peaksoft.configWebSocket.Message newMessage = new peaksoft.configWebSocket.Message(new User(null, null, user.getUsername()), null, null, Action.JOINED, Instant.now());
        simpMessagingTemplate.convertAndSend("/topic/messages", newMessage);

    }

    @EventListener
    public void handleSessionConnectEvent(SessionConnectEvent event) {
        System.out.println("Session Connect Event");
    }

    @EventListener
    public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        System.out.println("Session Disconnect Event");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        if (sessionAttributes == null) {
            return;
        }
        User user = (User) sessionAttributes.get("user");
        if (user == null) {
            return;
        }
        memberStore.removeMember(user);
        sendMembersList();

       Message message = new Message(new User(null, null, user.getUsername()), null, "", Action.LEFT, Instant.now());
        simpMessagingTemplate.convertAndSend("/topic/messages", message);

    }

    @MessageMapping("/message")
    public void getMessage(Message message) throws Exception {
        Message newMessage = new Message(new User(null, message.user().getSerialId(), message.user().getUsername()), message.receiverId(), message.comment(), message.action(), Instant.now());
        simpMessagingTemplate.convertAndSend("/topic/messages", newMessage);
    }

    @MessageMapping("/privatemessage")
    public void getPrivateMessage(Message message) throws Exception {
        Message newMessage = new Message(new User(null, message.user().getSerialId(), message.user().getUsername()), message.receiverId(), message.comment(), message.action(), Instant.now());
        simpMessagingTemplate.convertAndSendToUser(memberStore.getMember(message.receiverId()).getSerialId(), "/topic/privatemessages", newMessage);

    }

    private void sendMembersList() {
        List<User> memberList = memberStore.getMembersList();
        memberList.forEach(
                sendUser -> simpMessagingTemplate.convertAndSendToUser(sendUser.getSerialId(), "/topic/users", memberStore.filterMemberListByUser(memberList, sendUser)));
    }

    }

