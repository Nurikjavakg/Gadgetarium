package peaksoft.services;

import peaksoft.dto.user.SignInRequest;
import peaksoft.dto.user.SignUpRequest;
import peaksoft.dto.user.UserResponseWithToken;
import peaksoft.dto.simple.SimpleResponse;

public interface AuthenticationServcie {
    SimpleResponse signUp(SignUpRequest request);
    UserResponseWithToken login(SignInRequest signInRequest);

}
