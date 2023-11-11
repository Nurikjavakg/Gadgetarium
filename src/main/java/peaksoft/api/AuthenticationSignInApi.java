package peaksoft.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.user.SignInRequest;
import peaksoft.dto.user.SignUpRequest;
import peaksoft.dto.user.UserResponseWithToken;
import peaksoft.services.UserService;
import peaksoft.services.servicesImpl.AuthenticationServiceImpl;
import peaksoft.services.servicesImpl.UserServiceImpl;

@RestController
@RequestMapping("/api/admin/login")
@RequiredArgsConstructor
public class AuthenticationSignInApi {
    private final AuthenticationServiceImpl authenticationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseWithToken> login(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.login(signInRequest));
    }


        @Operation(summary = "Sign up",description = "Any user can register")
        @PostMapping("register")
        public SimpleResponse register(@RequestBody @Valid SignUpRequest registerRequest) {
            return authenticationService.signUp(registerRequest);

    }

}
