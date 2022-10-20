package ent.readcat.dto.book;

import java.time.LocalDateTime;

public class BookDto {
    private String title;
    private String author;
    private String description;
    private Integer totalPage;
    private Integer currentPage;
    private String state;
    private LocalDateTime lastReadTime;
    private LocalDateTime dateFinished;
    private String isbn;
    private String publisher;
    private String coverPath;
    private LocalDateTime readingTime;
    private boolean finished;
}
