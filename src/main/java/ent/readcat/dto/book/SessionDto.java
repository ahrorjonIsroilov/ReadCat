package ent.readcat.dto.book;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class SessionDto {
    private Long bookId;
    private LocalDateTime sessionDate;
    private LocalTime readingTime;
    private Integer page;
}
