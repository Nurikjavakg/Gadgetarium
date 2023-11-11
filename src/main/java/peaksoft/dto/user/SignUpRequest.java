package peaksoft.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import peaksoft.validation.EmailValidatian;
import peaksoft.validation.NotNullValidationClass;
import peaksoft.validation.PasswordValidatian;

@Builder
@Getter
@Setter
public class SignUpRequest{
        @NotNullValidationClass
        private String firstName;
        @NotNullValidationClass
        private String lastName;
        @EmailValidatian
        private String email;
        @PasswordValidatian
        private String password;
        private Boolean isAgree;


}