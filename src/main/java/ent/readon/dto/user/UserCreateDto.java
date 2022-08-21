package ent.readon.dto.user;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    private String nickname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
}