package ent.readon.mapper;

import ent.readon.dto.user.UserCreateDto;
import ent.readon.dto.user.UserDto;
import ent.readon.dto.user.UserUpdateDto;
import ent.readon.entity.user.AuthUser;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Mapper(componentModel = "spring")
public interface AuthMapper extends BaseMapper<AuthUser, UserDto, UserCreateDto, UserUpdateDto>, MyMapper {

    @Override
    UserDto toDto(AuthUser entity);

    @Override
    List<UserDto> toDto(List<AuthUser> entities);

    @Override
    default AuthUser fromCreateDto(UserCreateDto dto, PasswordEncoder encoder) {
        AuthUser user = new AuthUser();
        if (dto.getNickname() != null)
            user.setNickname(dto.getNickname());
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setEmailCode(UUID.randomUUID().toString());
        return user;
    }

    @Override
    default AuthUser fromUpdateDto(UserUpdateDto dto, AuthUser user) {
        if (dto.getAvatar() != null)
            user.setAvatar(dto.getAvatar());
        if (dto.getNickname() != null)
            user.setNickname(dto.getNickname());
        if (dto.getEmail() != null)
            user.setEmail(dto.getEmail());
        if (dto.getUsername() != null)
            user.setUsername(dto.getUsername());
        return user;
    }
}
