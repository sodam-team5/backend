package sodam.demo.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sodam.demo.domain.member.entity.Role;

import java.security.Key;
import java.util.Date;

/**
 * JWT 토큰을 생성하고 검증하는 클래스
 * - Access Token 과 Refresh Token 생성
 */
@Component
public class JwtTokenProvider {
    private final Key key;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtTokenProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.access-expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh-expiration}") long refreshTokenExpiration) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    /**
     * Access Token 생성
     */
    public String generateAccessToken(Long memberId, Role role) {
        return generateToken(memberId, role, accessTokenExpiration);
    }

    /**
     * Refresh Token 생성
     */
    public String generateRefreshToken(Long memberId,  Role role) {
        return generateToken(memberId, role, refreshTokenExpiration);
    }

    /**
     * JWT 토큰 생성
     */
    public String generateToken(Long memberId, Role role, long expirationTime) {
        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * JWT 토큰 검증 및 클레임 추출
     */
    public Claims validateToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(ExpiredJwtException e){
            throw new RuntimeException("JWT 토큰이 만료되었습니다.");
        }catch(JwtException e){
            throw new RuntimeException("JWT 토큰이 유효하지 않습니다.");
        }
    }

    /**
     * JWT 토큰이 만료되었는지 확인
     */
    public boolean isTokenExpired(String token){
        try{
            Date expiration = validateToken(token).getExpiration();
            return expiration.before(new Date());
        }catch(ExpiredJwtException e){
            return true;
        }
    }

    /**
     * JWT 토큰에서 'memberId' 추출
     */
    public Long getMemberIdFromToken(String token){
        Claims claims = validateToken(token);
        return Long.parseLong(claims.getSubject());
    }

    /**
     * JWT 토큰에서 'role' 추출
     */
    public Role getRoleFromToken(String token){
        Claims claims = validateToken(token);
        return Role.valueOf(claims.get("role", String.class));
    }
}
