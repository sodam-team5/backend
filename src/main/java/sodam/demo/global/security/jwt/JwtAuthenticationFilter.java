package sodam.demo.global.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 매 요청마다 JWT 를 확인하여 인증을 처리
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.extractToken(request);
        if(token != null){
            Long memberId = jwtTokenProvider.getMemberIdFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(memberId));

            if(userDetails != null){
              SecurityContextHolder.getContext().setAuthentication(
                      new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities())
              );
            }
        }
        filterChain.doFilter(request, response);
    }
}
