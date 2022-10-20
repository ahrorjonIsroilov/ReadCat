package ent.readcat.repo.book;

import ent.readcat.entity.book.Book;
import ent.readcat.entity.book.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepo extends JpaRepository<Quote, Long> {
    List<Quote> findAllByBook(Book book);
}
