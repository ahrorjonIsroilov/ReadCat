package ent.readon.dto.user;

import ent.readon.enums.Avatar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String nickname;
    private String username;
    private String email;
    private Avatar avatar;
}
