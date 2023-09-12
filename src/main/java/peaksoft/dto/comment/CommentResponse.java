package peaksoft.dto.comment;

import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record CommentResponse(
       Long id,
       String comment,
       ZonedDateTime createdDate
) {
    public CommentResponse {
    }
}
