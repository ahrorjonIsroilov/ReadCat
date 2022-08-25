package ent.readon.entity.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ent.readon.entity.Auditable;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "notes")
public class Note extends Auditable {
    @Column(nullable = false,columnDefinition = "text")
    private String title;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
