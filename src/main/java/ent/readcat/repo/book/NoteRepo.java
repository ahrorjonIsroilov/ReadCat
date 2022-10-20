package ent.readcat.repo.book;

import ent.readcat.entity.book.Book;
import ent.readcat.entity.book.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {

    List<Note> getAllByBook(Book book);
}
