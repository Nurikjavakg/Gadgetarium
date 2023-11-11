package peaksoft.services.servicesImpl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.user.SignInRequest;
import peaksoft.dto.user.SignUpRequest;
import peaksoft.dto.user.UserResponseWithToken;
import peaksoft.entities.User;
import peaksoft.enums.Role;
import peaksoft.exception.AccessDenied;
import peaksoft.exception.AlreadyExists;
import peaksoft.repository.UserRepository;
import peaksoft.services.AuthenticationServcie;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationServcie {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public SimpleResponse signUp(SignUpRequest request) {
        User userEmail = userRepository.findByEmail(request.getEmail());
        if (userEmail != null) {
            throw new AlreadyExists("User with email:" + request.getEmail() + " already exist");
        }
        if (!request.getIsAgree()) {
            throw new AccessDenied("You must first agree with our content...");
        }
        User user = convertRequestToUser(request);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        if (!user.getRole().equals(Role.ADMIN)) {
            userRepository.save(user);
        } else {
            throw new AccessDenied("You have not permission to signUp like as Admin...");
        }

        log.info("Successfully saved user with id: " + user.getId());
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved user with id: " + user.getId())
                .build();
    }

    private User convertRequestToUser(SignUpRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    @PostConstruct
    public void initSaveAdmin() {
        User user = User.builder()
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .build();
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }

    @Override
    public UserResponseWithToken login(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.email()).orElseThrow(() ->
                new RuntimeException(String.format("User with email: %s not found!", signInRequest.email())));

        String password = signInRequest.password();
        String dbEncodePassword = user.getPassword();

        if (!passwordEncoder.matches(password, dbEncodePassword)) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new UserResponseWithToken(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                token
        );
    }
}