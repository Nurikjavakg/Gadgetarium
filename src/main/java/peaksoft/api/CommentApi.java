package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import peaksoft.dto.comment.CommentRequest;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.messagingstompwebsocket.Greeting;
import peaksoft.messagingstompwebsocket.HelloMessage;
import peaksoft.services.CommentService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class CommentApi {
    private final CommentService commentService;

    @PostMapping("/comment/{productId}")
    @Secured("USER")
    public ResponseEntity<SimpleResponse> saveComment(@PathVariable Long productId , @RequestBody CommentRequest commentRequest){
        return ResponseEntity.ok( commentService.commentToProduct(productId,commentRequest));
    }

    @DeleteMapping("/delete/{productId}/{commentId}")
    public ResponseEntity<SimpleResponse> deleteComment(@PathVariable Long productId,@PathVariable Long commentId){
        return ResponseEntity.ok( commentService.deleteComment(productId,commentId));
    }



}
