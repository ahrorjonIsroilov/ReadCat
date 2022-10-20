package ent.readcat.controller.book;

import ent.readcat.controller.AbstractController;
import ent.readcat.dto.ApiResponse;
import ent.readcat.dto.book.*;
import ent.readcat.service.BookService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController extends AbstractController<BookService> {

    public BookController(BookService service) {
        super(service);
    }

    @PreAuthorize("hasAuthority('ADD_BOOK')")
    @PostMapping("/add/book")
    public HttpEntity<ApiResponse> addBook(@RequestBody BookCreateDto dto) {
        ApiResponse response = service.addBook(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('GET_BOOK')")
    @GetMapping("/get/book")
    public HttpEntity<?> getById(@RequestParam Long id){
        ApiResponse response = service.getById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('GET_ALL_BOOK')")
    @GetMapping("/getAll/book")
    public HttpEntity<ApiResponse> getAll(@RequestParam Long userId, @RequestParam int page) {
        ApiResponse r = service.getAll(userId, page);
        return ResponseEntity.status(r.isSuccess() ? 200 : 409).body(r);
    }

    @PreAuthorize("hasAuthority('DELETE_BOOK')")
    @DeleteMapping("/delete/book")
    public HttpEntity<ApiResponse> deleteBook(@RequestParam Long id) {
        ApiResponse response = service.deleteBook(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('EDIT_BOOK')")
    @PutMapping("/edit/book")
    public HttpEntity<ApiResponse> editBook(@RequestBody BookUpdateDto dto) {
        ApiResponse response = service.editBook(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('ADD_NOTE')")
    @PostMapping("/add/note")
    public HttpEntity<ApiResponse> addNote(@RequestBody NoteDto dto) {
        ApiResponse response = service.addNote(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('GET_ALL_NOTE')")
    @GetMapping("/getAll/note")
    public HttpEntity<ApiResponse> getAllNote(@RequestParam Long bookId) {
        ApiResponse response = service.getAllNote(bookId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('EDIT_NOTE')")
    @PostMapping("/edit/note")
    public HttpEntity<ApiResponse> editNote(@RequestBody NoteDto dto, @RequestParam Long id) {
        ApiResponse response = service.editNote(dto, id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('DELETE_NOTE')")
    @DeleteMapping("/delete/note")
    public HttpEntity<ApiResponse> deleteNote(@RequestParam Long id) {
        ApiResponse response = service.deleteNote(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('ADD_QUOTE')")
    @PostMapping("/add/quote")
    public HttpEntity<ApiResponse> addQuote(@RequestBody QuoteDto dto) {
        ApiResponse response = service.addQuote(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('GET_ALL_QUOTE')")
    @GetMapping("/getAll/quote")
    public HttpEntity<ApiResponse> getAllQuote(@RequestParam Long bookId) {
        ApiResponse response = service.getAllQuotes(bookId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('EDIT_QUOTE')")
    @PostMapping("/edit/quote")
    public HttpEntity<ApiResponse> editQuote(@RequestBody QuoteDto dto, @RequestParam Long id) {
        ApiResponse response = service.editQuote(dto, id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('DELETE_QUOTE')")
    @DeleteMapping("/delete/quote")
    public HttpEntity<ApiResponse> deleteQuote(@RequestParam Long id) {
        ApiResponse response = service.deleteQuote(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('ADD_SESSION')")
    @PostMapping("/add/session")
    public HttpEntity<ApiResponse> addSession(@RequestBody SessionDto dto) {
        ApiResponse response = service.addSession(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('GET_ALL_SESSION')")
    @GetMapping("/getAll/session")
    public HttpEntity<ApiResponse> getAllSession(@RequestParam Long bookId) {
        ApiResponse response = service.getAllSession(bookId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('DELETE_SESSION')")
    @DeleteMapping("/delete/session")
    public HttpEntity<ApiResponse> deleteSession(@RequestParam Long id) {
        ApiResponse response = service.deleteSession(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('EDIT_SESSION')")
    @PostMapping("/edit/session")
    public HttpEntity<ApiResponse> editQuote(@RequestBody SessionDto dto, @RequestParam Long id) {
        ApiResponse response = service.editSession(dto, id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('ADD_SHELVE')")
    @PostMapping("/add/shelve")
    public HttpEntity<ApiResponse> addShelve(@RequestBody ShelveDto dto) {
        ApiResponse response = service.addShelve(dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('GET_ALL_SHELVE')")
    @GetMapping("/getAll/shelve")
    public HttpEntity<ApiResponse> getAllShelves(@RequestParam Long bookId) {
        ApiResponse response = service.getAllShelves(bookId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('EDIT_SHELVE')")
    @PostMapping("/edit/shelve")
    public HttpEntity<ApiResponse> editShelve(@RequestBody ShelveDto dto, @RequestParam Long id) {
        ApiResponse response = service.editShelve(dto, id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @PreAuthorize("hasAuthority('DELETE_SHELVE')")
    @DeleteMapping("/delete/shelve")
    public HttpEntity<ApiResponse> deleteShelve(@RequestParam Long id) {
        ApiResponse response = service.deleteShelve(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
