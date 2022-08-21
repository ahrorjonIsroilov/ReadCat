package ent.readon.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Validator<T extends Serializable> {
    public T returnType;
    public Boolean success;

    public Validator(Boolean success) {
        this.success = success;
    }
}
