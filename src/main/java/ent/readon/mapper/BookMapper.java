package ent.readon.mapper;

import ent.readon.dto.book.BookCreateDto;
import ent.readon.dto.book.BookDto;
import ent.readon.dto.book.BookUpdateDto;
import ent.readon.entity.book.Book;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<Book, BookDto, BookCreateDto, BookUpdateDto> {
    @Override
    BookDto toDto(Book entity);

    @Override
    List<BookDto> toDto(List<Book> entities);

    @Override
    Book fromCreateDto(BookCreateDto createDto, PasswordEncoder passwordEncoder);

    @Override
    default Book fromUpdateDto(BookUpdateDto dto, Book book) {
        if(dto.getCoverPath()!=null)
            book.setCoverPath(dto.getCoverPath());
        if(dto.getAuthor()!=null)
            book.setAuthor(dto.getAuthor());
        if(dto.getCurrentPage()!=null)
            book.setCurrentPage(dto.getCurrentPage());
        if(dto.getDescription()!=null)
            book.setDescription(dto.getDescription());
        if(dto.getIsbn()!=null)
            book.setIsbn(dto.getIsbn());
        if(dto.getPublisher()!=null)
            book.setPublisher(dto.getPublisher());
        if(dto.getLastReadTime()!=null)
            book.setLastReadTime(dto.getLastReadTime());
        if(dto.getState()!=null)
            book.setState(dto.getState());
        if(dto.getDateFinished()!=null)
            book.setDateFinished(dto.getDateFinished());
        if(dto.getTitle()!=null)
            book.setTitle(dto.getTitle());
        if(dto.getTotalPage()!=null)
            book.setTotalPage(dto.getTotalPage());
        if(dto.getFinished()!=null)
            book.setFinished(dto.getFinished());
        return book;
    }

    ;
}
