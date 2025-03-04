package sodam.demo.global.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sodam.demo.domain.member.entity.Role;

/**
 * 토큰 추출 및 검증하는 클래스
 */
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Access Token 검증
     */
    public boolean validateAccessToken(String token) {
        return !jwtTokenProvider.isTokenExpired(token);
    }

    /**
     * Refresh Token 검증
     */
    public boolean validateRefreshToken(String token) {
        return !jwtTokenProvider.isTokenExpired(token);
    }

    /**
     * Access Token 재발급
     */
    public String reissueAccessToken(String token) {
        if(validateAccessToken(token)) {
            Long memberId = jwtTokenProvider.getMemberIdFromToken(token);
            Role role = jwtTokenProvider.getRoleFromToken(token);
            return jwtTokenProvider.generateAccessToken(memberId, role);
        }
        throw new RuntimeException("유효하지 않은 Refresh Token 입니다.");
    }

    /**
     * 헤더에서 JWT 토큰 추출
     */
    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
}
