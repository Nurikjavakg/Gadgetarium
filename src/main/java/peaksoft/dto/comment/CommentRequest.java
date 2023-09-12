package peaksoft.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entities.Comment;

@Builder
public record CommentRequest(String comment){


    public Comment build() {
        Comment comment = new Comment();
        comment.setComment(this.comment);
        return comment;
    }
}