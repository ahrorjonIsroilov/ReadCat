package ent.readon.mapper;

import ent.readon.entity.user.AuthUser;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface BaseMapper<E, D, CD, UD> extends MyMapper {
    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromCreateDto(CD createDto, PasswordEncoder passwordEncoder);

    E fromUpdateDto(UD updateDto, AuthUser user);
}
