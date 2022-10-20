package ent.readcat.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteDto {
    private String title;
    private Integer page;
    private Long bookId;
}
