package ent.readon.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import ent.readon.entity.Auditable;
import ent.readon.entity.Role;
import ent.readon.enums.Avatar;
import ent.readon.enums.Permissions;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class AuthUser extends Auditable implements UserDetails {
    private String nickname;
    @NotNull
    @Column(unique = true, nullable = false)
    private String username;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Avatar avatar;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;
    @NotNull
    @Column(unique = true, nullable = false)
    @Email
    private String email;
    @JsonIgnore
    @Column(name = "email_code")
    private String emailCode;
    @JsonIgnore
    @Column(name = "email_code_expiry")
    private LocalDateTime emailCodeExpiry;
    @Column(name = "current_book_id")
    private Long currentBookId;
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;
    private boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(this.role.getPermissions().toArray(Permissions[]::new)).forEach(perm -> authorities.add(new SimpleGrantedAuthority(perm.name())));
        return authorities;
    }

}
