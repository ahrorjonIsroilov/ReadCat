package ent.readcat.config.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import ent.readcat.config.jwt.JwtUtils;
import ent.readcat.service.user.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class MyAuthorizationFilter extends OncePerRequestFilter {


    private final JwtUtils jwtUtils;

    private final AuthService service;

    public MyAuthorizationFilter(JwtUtils jwtUtils, AuthService service) {
        this.jwtUtils = jwtUtils;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.nonNull((authHeader)) && authHeader.startsWith("Bearer")) {
            try {
                String token = authHeader.substring("Bearer ".length());
                DecodedJWT decodedJWT = jwtUtils.getVerifier().verify(token);
                String username = decodedJWT.getSubject();
                UserDetails userDetails = service.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (JWTVerificationException ignored) {

            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
