package ent.readon.dto.user;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String nickname;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    private String emailCode;
    private String emailCodeExpiry;
    private Long currentBookId;
}
