package ent.readon.config.jwt;

import com.auth0.jwt.JWT;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class JwtProvider extends JwtUtils {


    public String generateToken(String username, HttpServletRequest request) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(getExpiration())
                .withIssuer(request.getRequestURL().toString())
                .sign(getAlgorithm());
    }
}

