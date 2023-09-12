package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import peaksoft.services.servicesImpl.AuthenticationServiceImpl;
import peaksoft.dto.user.SignInRequest;
import peaksoft.dto.user.UserResponseWithToken;


@RestController
@RequestMapping("/api/admin/login")
@RequiredArgsConstructor
public class AuthenticationSignInApi {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping
    public ResponseEntity<UserResponseWithToken> login(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.login(signInRequest));
    }

}