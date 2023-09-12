package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.userInfo.PaginationResponse;
import peaksoft.dto.userInfo.UserRequestInfo;
import peaksoft.dto.userInfo.UserResponseInfo;
import peaksoft.services.UserService;
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public PaginationResponse companies(@RequestParam int currentPage, @RequestParam int pageSize) {
        return userService.getAllUsers(currentPage, pageSize);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponseInfo getCompanyById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateUserById(@PathVariable Long userId, @RequestBody UserRequestInfo userRequestInfo) {
        userService.updateUser(userId, userRequestInfo);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Company with name: %s successfully updated!", userRequestInfo.firstName()))
                .build();
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("company is deleted...")
                .build();
    }
}
