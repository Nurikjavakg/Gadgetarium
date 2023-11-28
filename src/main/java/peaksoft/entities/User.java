package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.enums.Action;
import peaksoft.enums.Role;
import peaksoft.exception.NotFoundException;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String serialId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isAgree;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Action action;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Favorite> favorites;
    @OneToOne(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private Basket basket;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Comment> comments;
//    @OneToMany(mappedBy = "user",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
//    private List<Chat> chats;

    public User(Long id, String serialId, String email) {
        this.id = id;
        this.serialId = serialId;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

    public void addComment(Comment comment1) {
        if (comment1 != null){
            comments.add(comment1);
            comment1.setUser(this);
        }else {
            throw  new RuntimeException("Comment is null!!!");
        }

    }

    public void addFavorite(Favorite favorite) {
        if(favorite != null){
            favorites.add(favorite);
            favorite.setUser(this);
        }else {
            throw new NotFoundException("Favorite is null...");
        }
    }
}