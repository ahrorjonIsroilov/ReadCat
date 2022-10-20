package ent.readcat.repo.book;

import ent.readcat.entity.book.Book;
import ent.readcat.entity.book.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepo extends JpaRepository<Session,Long> {

    List<Session> findAllByBook(Book book);
}
