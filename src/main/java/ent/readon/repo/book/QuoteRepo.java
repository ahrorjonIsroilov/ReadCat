package ent.readon.repo.book;

import ent.readon.entity.book.Book;
import ent.readon.entity.book.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepo extends JpaRepository<Quote, Long> {
    List<Quote> findAllByBook(Book book);
}
