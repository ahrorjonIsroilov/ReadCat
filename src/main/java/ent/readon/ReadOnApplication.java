package ent.readon;

import ent.readon.entity.Role;
import ent.readon.entity.user.AuthUser;
import ent.readon.enums.Avatar;
import ent.readon.enums.Permissions;
import ent.readon.enums.RoleName;
import ent.readon.repo.AuthRepo;
import ent.readon.repo.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ReadOnApplication implements CommandLineRunner {

    final AuthRepo authRepo;
    final RoleRepo roleRepo;
    final PasswordEncoder encoder;

    public ReadOnApplication(AuthRepo authRepo, RoleRepo roleRepo, PasswordEncoder encoder) {
        this.authRepo = authRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadOnApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        migrate();
    }

    private void migrate() {
        Set<Permissions> permissions = new HashSet<>();
        for (Permissions value : Permissions.values()) {
            if (value.isInGroup(Permissions.Group.USER)) permissions.add(value);
        }
        Role role = Role.builder().name(RoleName.USER).permissions(permissions).build();
        roleRepo.save(role);
        AuthUser user = AuthUser.builder()
                .username("Aron")
                .avatar(Avatar.MAN_2)
                .password(encoder.encode("123"))
                .email("readooon@gmail.com")
                .nickname("Ahrorjon")
                .role(roleRepo.findByName(RoleName.USER))
                .build();
        authRepo.save(user);
    }
}
