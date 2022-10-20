package ent.readcat.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class JwtUtils {
    private final Calendar c = new GregorianCalendar();

    protected Date getExpiration() {
        c.set(2099, Calendar.DECEMBER, 31);
        return c.getTime();
    }

    protected Algorithm getAlgorithm() {
        String secret = "ufVLItfiBLhjhgcYTIrdOFP68g7uhuKBGI7IDCOVUg";
        return Algorithm.HMAC256(Base64.getDecoder().decode(secret.getBytes()));
    }

    public JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
