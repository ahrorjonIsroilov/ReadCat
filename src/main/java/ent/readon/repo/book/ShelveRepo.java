package ent.readon.repo.book;

import ent.readon.entity.book.Shelve;
import ent.readon.entity.user.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelveRepo extends JpaRepository<Shelve,Long> {

    List<Shelve> findAllByUser(AuthUser user);
}
