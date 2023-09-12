package peaksoft.dto.userInfo;

import java.time.ZonedDateTime;

public record UserRequestInfo(
        String firstName,
        String lastName,
        String email,
        String password,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
