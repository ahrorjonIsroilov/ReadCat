package ent.readcat.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookUpdateDto {
    private Long id;
    private String title;//
    private String author;//
    private String description;//
    private Integer totalPage;//
    private Integer currentPage;//
    private String state;//
    private LocalDateTime lastReadTime;//
    private LocalDateTime dateFinished;//
    private String isbn;//
    private String publisher;//
    private String coverPath;//
    private LocalDateTime readingTime;//
    private Boolean finished;
}
