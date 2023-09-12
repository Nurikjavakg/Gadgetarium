package peaksoft.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignUpRequest{
        private String firstName;
        private String lastName;
        private String email;
        private String password;


}