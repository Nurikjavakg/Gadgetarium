package peaksoft.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.user.UserResponse;
import peaksoft.dto.userInfo.PaginationResponse;
import peaksoft.dto.userInfo.UserRequestInfo;
import peaksoft.dto.userInfo.UserResponseInfo;
import peaksoft.entities.User;
import peaksoft.enums.Role;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.UserRepository;
import peaksoft.services.UserService;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

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
        log.info("Get by id:"+userId+" found");
        return userRepository.getUserById(Role.USER,userId)
                .orElseThrow(()-> {
                    log.info("Get by id:"+userId+" not found");
                    return new NotFoundException("User with id:"+userId+" not exists");
                });


    }

    @Override
    public SimpleResponse updateUser(Long userId, UserRequestInfo userRequestInfo) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->{
                    log.info("User not found...");
                    return new NoSuchElementException("User not found...");
                });

        user.setFirstName(userRequestInfo.firstName());
        user.setLastName(userRequestInfo.lastName());
        user.setEmail(userRequestInfo.email());
        user.setPassword(userRequestInfo.password());
        user.setUpdatedAt(userRequestInfo.updatedAt());
        userRepository.save(user);
        log.info("User with id:"+userId+" updated...");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: %s successfully updated", user.getId()))
                .build();
    }


    @Override
    public SimpleResponse deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->{
                    log.info("User with id:" + userId + "not found...");
                    return new NotFoundException("Company with id:" + userId + "not found...");
                });
        userRepository.delete(user);
        log.info("COMPANY IS DELETED...");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("COMPANY IS DELETED...")
                .build();


    }
}
