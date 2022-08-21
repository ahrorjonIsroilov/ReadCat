package ent.readon.config.auditing;

import ent.readon.entity.user.AuthUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuditingObject implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser"))
            return Optional.of(((AuthUser) authentication.getPrincipal()).getId());
        return Optional.empty();
    }
}
