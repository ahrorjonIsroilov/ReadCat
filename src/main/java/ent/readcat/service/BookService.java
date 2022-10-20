package ent.readcat.service;

import ent.readcat.dto.ApiResponse;
import ent.readcat.dto.book.*;
import ent.readcat.entity.book.*;
import ent.readcat.mapper.BookMapper;
import ent.readcat.repo.AuthRepo;
import ent.readcat.repo.book.*;
import ent.readcat.service.user.BaseService;
import ent.readcat.utils.Utils;
import ent.readcat.utils.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService extends AbstractService<BookRepo, BookMapper> implements BaseService {

    private final Utils utils;
    private final NoteRepo noteRepo;
    private final QuoteRepo quoteRepo;
    private final SessionRepo sessionRepo;
    private final ShelveRepo shelveRepo;
    private final AuthRepo authRepo;

    public BookService(BookRepo repo, @Qualifier("bookMapperImpl") BookMapper mapper, Utils utils, NoteRepo noteRepo, QuoteRepo quoteRepo, SessionRepo sessionRepo, ShelveRepo shelveRepo, AuthRepo authRepo) {
        super(repo, mapper);
        this.utils = utils;
        this.noteRepo = noteRepo;
        this.quoteRepo = quoteRepo;
        this.sessionRepo = sessionRepo;
        this.shelveRepo = shelveRepo;
        this.authRepo = authRepo;
    }

    public ApiResponse getById(Long id) {
        if (repo.findById(id).isEmpty())
            return new ApiResponse("Book not found!", false);
        return new ApiResponse(repo.findById(id).get(), true);
    }

    public ApiResponse getAll(Long userId, Integer page) {
        if (!authRepo.existsById(userId)) return new ApiResponse("User doesn't exists!", false);
        List<Book> books = repo.findAllByUser(authRepo.getReferenceById(userId), PageRequest.of(page, 10));
        return new ApiResponse(books, true);
    }

    public ApiResponse addBook(BookCreateDto dto) {
        Book book = mapper.fromCreateDto(dto, null);
        book.setUser(authRepo.getReferenceById(dto.getUserId()));
        book.setCurrentPage(0);
        Validator<?> validator = utils.validForAddBook(dto);
        if (validator.success) {
            repo.save(book);
            return new ApiResponse("Added!", true);
        }
        return new ApiResponse(validator.returnType, false);
    }

    public ApiResponse deleteBook(Long id) {
        if (!repo.existsById(id)) return new ApiResponse("Book doesn't exists!", false);
        repo.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    public ApiResponse editBook(BookUpdateDto dto) {
        Optional<Book> byId = repo.findById(dto.getId());
        if (byId.isEmpty()) return new ApiResponse("Book not found", false);
        Book book = mapper.fromUpdateDto(dto, byId.get());
        repo.save(book);
        return new ApiResponse("Updated!", true);
    }

    public ApiResponse addNote(NoteDto dto) {
        Validator<?> validator = utils.validForAddNote(dto);
        if (validator.success) {
            if (!repo.existsById(dto.getBookId())) return new ApiResponse("Book not found!", false);
            Note note = Note.builder().title(dto.getTitle()).book(repo.getReferenceById(dto.getBookId())).build();
            noteRepo.save(note);
            return new ApiResponse("Added!", true);
        } else return new ApiResponse(validator.returnType, false);
    }

    public ApiResponse getAllNote(Long bookId) {
        if (!repo.existsById(bookId)) return new ApiResponse("Book doesn't exists!", false);
        List<Note> allNotes = noteRepo.getAllByBook(repo.getReferenceById(bookId));
        return new ApiResponse(allNotes, true);
    }

    public ApiResponse editNote(NoteDto dto, Long id) {
        Optional<Note> byId = noteRepo.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("Not found!", false);
        byId.get().setTitle(dto.getTitle());
        noteRepo.save(byId.get());
        return new ApiResponse("Updated!", true);
    }

    public ApiResponse deleteNote(Long id) {
        if (noteRepo.existsById(id)) return new ApiResponse("Note doesn't exists!", false);
        noteRepo.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    public ApiResponse addQuote(QuoteDto dto) {
        Validator<?> validator = utils.validForAddQuote(dto);
        if (validator.success) {
            if (!repo.existsById(dto.getBookId())) return new ApiResponse("Book not found", false);
            Quote quote = Quote.builder().title(dto.getTitle()).page(dto.getPage()).book(repo.getReferenceById(dto.getBookId())).build();
            quoteRepo.save(quote);
            return new ApiResponse("Added!", true);
        } else return new ApiResponse(validator.returnType, false);
    }

    public ApiResponse getAllQuotes(Long bookId) {
        if (!repo.existsById(bookId)) return new ApiResponse("Book doesn't exists!", false);
        List<Quote> quotes = quoteRepo.findAllByBook(repo.getReferenceById(bookId));
        return new ApiResponse(quotes, true);
    }

    public ApiResponse editQuote(QuoteDto dto, Long id) {
        Optional<Quote> byId = quoteRepo.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("Not found!", false);
        if (dto.getTitle() != null) byId.get().setTitle(dto.getTitle());
        if (dto.getPage() != null) byId.get().setPage(dto.getPage());
        quoteRepo.save(byId.get());
        return new ApiResponse("Updated!", true);
    }

    public ApiResponse deleteQuote(Long id) {
        if (quoteRepo.existsById(id)) return new ApiResponse("Quote doesn't exists!", false);
        quoteRepo.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    public ApiResponse addSession(SessionDto dto) {
        if (dto.getBookId() == null) return new ApiResponse("Book id required", false);
        if (!repo.existsById(dto.getBookId())) return new ApiResponse("Book doesn't exists!", false);
        if (dto.getSessionDate() == null) return new ApiResponse("Date required", false);
        if (dto.getReadingTime() == null) return new ApiResponse("Reading time required", false);
        Session session = Session.builder()
                .sessionDate(dto.getSessionDate())
                .readingTime(dto.getReadingTime())
                .page(dto.getPage())
                .book(repo.getReferenceById(dto.getBookId()))
                .build();
        sessionRepo.save(session);
        return new ApiResponse("Added!", true);
    }

    public ApiResponse getAllSession(Long bookId) {
        if (!repo.existsById(bookId)) return new ApiResponse("Book not found!", false);
        List<Session> sessionRepoAllByBook = sessionRepo.findAllByBook(repo.getReferenceById(bookId));
        return new ApiResponse(sessionRepoAllByBook, true);
    }

    public ApiResponse deleteSession(Long id) {
        if (!sessionRepo.existsById(id)) return new ApiResponse("Session not found!", false);
        sessionRepo.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    public ApiResponse editSession(SessionDto dto, Long id) {
        if (!sessionRepo.existsById(id)) return new ApiResponse("Session not found!", false);
        Session session = sessionRepo.getReferenceById(id);
        if (dto.getPage() != null) session.setPage(dto.getPage());
        if (dto.getSessionDate() != null) session.setSessionDate(dto.getSessionDate());
        if (dto.getReadingTime() != null) session.setReadingTime(dto.getReadingTime());
        sessionRepo.save(session);
        return new ApiResponse("Updated!", true);
    }

    public ApiResponse addShelve(ShelveDto dto) {
        if (dto.getUserId() == null) return new ApiResponse("User id required!", false);
        if (!authRepo.existsById(dto.getUserId())) return new ApiResponse("User not found!", false);
        if (dto.getTitle() == null) return new ApiResponse("Title required!", false);
        Shelve shelve = Shelve.builder()
                .title(dto.getTitle())
                .user(authRepo.getReferenceById(dto.getUserId()))
                .build();
        shelveRepo.save(shelve);
        return new ApiResponse("Added!", true);
    }

    public ApiResponse getAllShelves(Long userId) {
        if (!repo.existsById(userId)) return new ApiResponse("User not found!", false);
        List<Shelve> shelves = shelveRepo.findAllByUser(authRepo.getReferenceById(userId));
        return new ApiResponse(shelves, true);
    }

    public ApiResponse editShelve(ShelveDto dto, Long id) {
        if (!repo.existsById(dto.getUserId())) return new ApiResponse("User not found!", false);
        if (!shelveRepo.existsById(id)) return new ApiResponse("Shelve not found!", false);
        Shelve shelve = shelveRepo.getReferenceById(id);
        if (dto.getTitle() != null) shelve.setTitle(dto.getTitle());
        shelveRepo.save(shelve);
        return new ApiResponse("Edited!", true);
    }

    public ApiResponse deleteShelve(Long id) {
        if (!shelveRepo.existsById(id)) return new ApiResponse("Shelve not found!", false);
        shelveRepo.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }
}
