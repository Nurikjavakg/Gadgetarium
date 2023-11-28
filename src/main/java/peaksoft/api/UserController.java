package peaksoft.api;


import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import peaksoft.services.servicesImpl.WebSocketUserService;
import peaksoft.entities.User;


@Controller
@Tag(name = "Chat API")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserController {
    private final WebSocketUserService webSocketUserService;
//
//
//    @MessageMapping("/user.addUser")
//    @SendTo("user/topic")
//    public User addUser(@Payload User user){
//        webSocketUserService.saveUser(user);
//        return user;
//    }
//
//    @MessageMapping("/user.addUser")
//    @SendTo("user/topic")
//    public User disconnect(@Payload User user){
//        webSocketUserService.disconnect(user);
//        return user;
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>>getAllUser(){
//        return ResponseEntity.ok(webSocketUserService.getConnectedUsers());
//    }
    }

