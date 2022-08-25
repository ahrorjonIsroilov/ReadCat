package ent.readon.repo.book;

import ent.readon.entity.book.Book;
import ent.readon.entity.user.AuthUser;
import ent.readon.repo.BaseRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Long>, BaseRepo {

    List<Book> findAllByUser(AuthUser user, Pageable pageable);
}
