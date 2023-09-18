package peaksoft.services.servicesImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.userInfo.PaginationResponse;
import peaksoft.dto.userInfo.UserRequestInfo;
import peaksoft.dto.userInfo.UserResponseInfo;
import peaksoft.entities.User;
import peaksoft.enums.Role;
import peaksoft.exception.AccessDenied;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.UserRepository;
import peaksoft.services.UserService;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PaginationResponse getAllUsers( int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<UserResponseInfo> getAllUsers = userRepository.getAllCompanies(Role.USER,pageable);
        log.info("Get all users method is able...");
        return PaginationResponse.builder()
                .userResponseInfoList(getAllUsers.getContent())
                .currentPage(getAllUsers.getNumber()+1)
                .pageSize(getAllUsers.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse assign(Long userId, Long basketId) {
        return null;
    }

    @Override
    public UserResponseInfo getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User with id:"+userId+" not found..."));
        if (user.getRole() == Role.ADMIN) {
            throw new NotFoundException("User with id:" + userId + " not found...");
        }
        log.info("Get by id:"+userId+" found");
        return userRepository.getUserById(Role.USER,userId)
                .orElseThrow(()-> {
                    log.info("Get by id:"+userId+" not found");
                    return new NotFoundException("User with id:"+userId+" not exists");
                });


    }

    @Override
    @Transactional
    public SimpleResponse updateUser(Long userId, UserRequestInfo userRequestInfo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to delete a comment !!!");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->{
                    log.error("User not found...");
                    return new NotFoundException("User not found...");
                });
        if (user.getRole() == Role.ADMIN) {
            throw new AccessDenied("You do not have permission to delete an admin.");
        }else {
            user.setFirstName(userRequestInfo.firstName());
            user.setLastName(userRequestInfo.lastName());
            user.setEmail(userRequestInfo.email());
            user.setPassword(passwordEncoder.encode(userRequestInfo.password()));
            user.setUpdatedAt(userRequestInfo.updatedAt());

            log.info("User with id:" + userId + " updated...");
        }
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: %s successfully updated", user.getId()))
                .build();
    }


    @Override
    public SimpleResponse deleteUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to delete a comment !!!");
        }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> {
                        log.info("User with id:" + userId + " not found...");
                        return new NotFoundException("User with id:" + userId + " not found...");
                    });
        if (user.getRole() == Role.ADMIN) {
            throw new AccessDenied("You do not have permission to delete an admin.");
        } else {
            userRepository.delete(user);
        }
            log.info("User is deleted with id:" + userId + "...");
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("User with id:" + userId + " has been deleted.")
                    .build();
        }

    }
