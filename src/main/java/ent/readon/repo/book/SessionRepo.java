package ent.readon.repo.book;

import ent.readon.entity.book.Book;
import ent.readon.entity.book.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepo extends JpaRepository<Session,Long> {

    List<Session> findAllByBook(Book book);
}
