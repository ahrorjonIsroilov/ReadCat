package ent.readcat.entity.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ent.readcat.entity.Auditable;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "quotes")
public class Quote extends Auditable {
    @Column(nullable = false,columnDefinition = "text")
    private String title;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
    @Column(nullable = false)
    private Integer page;
}
