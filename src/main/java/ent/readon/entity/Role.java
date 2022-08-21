package ent.readon.entity;

import ent.readon.enums.Permissions;
import ent.readon.enums.RoleName;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "roles")
public class Role extends Auditable {
    @Enumerated(value = EnumType.STRING)
    private RoleName name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Permissions> permissions;
}
