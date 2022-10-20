package ent.readcat.dto.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookCreateDto {
    @NotNull
    private Long userId;
    @NotNull
    private String title;
    @NotNull
    private String author;
    private String description;
    @NotNull
    private Integer totalPage;
    private String isbn;
    private String publisher;
    private String coverPath;
}
