package ent.readcat.repo;

import ent.readcat.entity.user.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<AuthUser, Long>, BaseRepo {

    Optional<AuthUser> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmailAndIdIsNotLike(@Email String email, Long id);

    Boolean existsByUsernameAndIdIsNotLike(String username, Long id);

    Boolean existsByEmail(@Email String email);

    boolean existsByEmailAndEmailCode(@Email String email, String code);

    boolean existsByEmailCodeAndEmailCodeExpiryBefore(String emailCode, LocalDateTime emailCodeExpiry);

    AuthUser findByEmail(@Email String email);
}
