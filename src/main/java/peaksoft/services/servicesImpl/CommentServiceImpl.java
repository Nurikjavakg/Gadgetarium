package peaksoft.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.comment.CommentRequest;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Comment;
import peaksoft.entities.Product;
import peaksoft.entities.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.services.CommentService;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Override
    public SimpleResponse commentToProduct(Long productId, CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(()-> new NotFoundException("User with id:"+email+" not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product with id:"+productId+" not found"));

        Comment comment1 = commentRequest.build();
        user.addComment(comment1);
        product.getComments().add(comment1);
        comment1.setProduct(product);
        commentRepository.save(comment1);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Lesson is updated...")
                .build();

    }

    @Override
    public SimpleResponse deleteComment(Long commentId) {
        return null;
    }
}
