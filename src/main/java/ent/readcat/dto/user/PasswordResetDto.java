package ent.readcat.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordResetDto {
    @NotNull
    private Integer code;
    @NotNull
    private String password;
    @NotNull
    private String prePassword;
    @NotNull
    private String email;
}
