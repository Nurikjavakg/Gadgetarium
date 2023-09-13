package peaksoft.services;

import peaksoft.dto.comment.CommentRequest;
import peaksoft.dto.simple.SimpleResponse;

public interface CommentService {
    SimpleResponse commentToProduct(Long productId, CommentRequest commentRequest);
    SimpleResponse deleteComment(Long productId,Long commentId);
}
