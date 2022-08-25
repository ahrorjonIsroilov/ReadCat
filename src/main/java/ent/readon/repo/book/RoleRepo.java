package ent.readon.repo.book;

import ent.readon.entity.Role;
import ent.readon.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);
}
