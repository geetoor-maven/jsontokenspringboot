package id.co.geetoor.demoauth.util;

import id.co.geetoor.demoauth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@Component
public class JwtUtils {

    private static String secret = "this_is_secret";
    private static long expiryDuration = 60 * 60;

    public String generateJwt(User user){

        long milTime = System.currentTimeMillis();
        long expTime = milTime + expiryDuration * 1000;

        Date issueAt = new Date(milTime);
        Date expiredTime = new Date(expTime);

        // klaim token
        Claims claims = Jwts.claims()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issueAt)
                .setExpiration(expiredTime);

        // type ini akan di liat ketika token udah di generate
        claims.put("type", user.getUserType());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());

        // kembalikan data token dengan tipe String yang sudah di hash melalui objek claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public void verify(String authorization) throws Exception{
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);
        }catch (Exception e){
            throw new AccessDeniedException("Acceesss Denied..");
        }
    }
}
