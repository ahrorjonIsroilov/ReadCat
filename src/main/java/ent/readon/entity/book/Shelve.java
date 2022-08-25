package ent.readon.entity.book;

import ent.readon.entity.Auditable;
import ent.readon.entity.user.AuthUser;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "shelves")
public class Shelve extends Auditable {
    private String  title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private AuthUser user;
    @OneToMany(mappedBy = "shelve",fetch = FetchType.LAZY)
    private List<Book> books;
}
