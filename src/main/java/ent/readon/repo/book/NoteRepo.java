package ent.readon.repo.book;

import ent.readon.entity.book.Book;
import ent.readon.entity.book.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {

    List<Note> getAllByBook(Book book);
}
