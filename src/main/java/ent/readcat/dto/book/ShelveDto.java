package ent.readcat.dto.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShelveDto {
    @NotNull
    private Long userId;
    @NotNull
    private String title;
}
