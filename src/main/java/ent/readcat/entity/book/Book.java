package ent.readcat.entity.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ent.readcat.entity.Auditable;
import ent.readcat.entity.user.AuthUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "books")
public class Book extends Auditable {
    private String title;
    private String author;
    private String description;
    @Column(name = "total_page", nullable = false)
    private Integer totalPage;
    @Column(name = "current_page", nullable = false)
    private Integer currentPage;
    private String state;
    @Column(name = "last_read_time")
    private LocalDateTime lastReadTime;
    @Column(name = "date_finished")
    private LocalDateTime dateFinished;
    private String isbn;
    private String publisher;
    private String coverPath;
    @Column(name = "reading_time")
    private LocalDateTime readingTime;
    private boolean finished;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelve_id")
    private Shelve shelve;
    @JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Quote> quotes;
    @JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Note> notes;
    @JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Set<Session> sessions;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser user;
}
