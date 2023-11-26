package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.userInfo.UserResponseInfo;
import peaksoft.entities.User;
import peaksoft.enums.Action;
import peaksoft.enums.Role;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT NEW peaksoft.dto.userInfo.UserResponseInfo(u.id, u.firstName, u.lastName, u.email, u.createdAt, u.updatedAt) FROM User u where u.role = :role")
    Page<UserResponseInfo> getAllCompanies(Role role, Pageable pageable);
    @Query("select new peaksoft.dto.userInfo.UserResponseInfo(u.id, u.firstName, u.lastName, u.email, u.createdAt, u.updatedAt)from User u where u.id = :userId and u.role = :role")
    Optional<UserResponseInfo> getUserById(Role role,Long userId);


    boolean existsByEmail(String email);

    Optional<User> getUserByEmail(String email);


    User findByEmail(String email);


    List<User> findAllByAction(Action action);
}

