package peaksoft.dto.userInfo;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class UserResponseInfo{
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

    public UserResponseInfo(Long id, String firstName, String lastName, String email, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
