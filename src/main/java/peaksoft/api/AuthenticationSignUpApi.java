package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.services.servicesImpl.AuthenticationServiceImpl;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.user.SignUpRequest;

@RestController
@RequestMapping("/api/admin/signUp")
@RequiredArgsConstructor
public class AuthenticationSignUpApi {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping
    public ResponseEntity<SimpleResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));

    }
}