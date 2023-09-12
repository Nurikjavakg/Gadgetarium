package peaksoft.services;

import peaksoft.dto.simple.SimpleResponse;
import peaksoft.dto.user.UserResponse;
import peaksoft.dto.userInfo.PaginationResponse;
import peaksoft.dto.userInfo.UserRequestInfo;
import peaksoft.dto.userInfo.UserResponseInfo;
import peaksoft.entities.User;

import java.util.List;

public interface UserService {
    PaginationResponse getAllUsers(int currentPage, int pageSize);
    SimpleResponse assign(Long userId, Long basketId);
    UserResponseInfo getUserById(Long userId);
    SimpleResponse updateUser(Long userId, UserRequestInfo userRequestInfo);
    SimpleResponse deleteUser(Long userId);


}
