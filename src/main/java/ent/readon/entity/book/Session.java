package ent.readon.entity.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ent.readon.entity.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "sessions")
public class Session extends Auditable {
    private LocalDateTime sessionDate;
    private LocalTime readingTime;
    private Integer page;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
