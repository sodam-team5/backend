package sodam.demo.global.security.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sodam.demo.domain.member.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * Spring Security 에서 사용자 인증 정보를 저장하는 클래스
 * 'UserDetails' 를 구현하여 Spring Security 의 인증 시스템과 연동
 * 해당 객체를 기반으로 사용자 인증을 처리할 수 있음
 * - 'Guardian(보호자)" : 이메일(email) + 비밀번호(password)
 * - 'Elder(노인)' : Elder 이름 + Guardian 이름 조합 (elderName:guardianName), 비밀번호 없음
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final Long memberId;
    private final String username; // Guardian: email, Elder: elderName:guardianName
    private final String password; // Guardian : password, Elder : null
    private final Role role; // ROLE_GUARDIAN or ROLE_ELDER

    public Long getMemberId(){
        return this.memberId;
    }

    public Role getRole(){
        return this.role;
    }

    /**
     * 사용자의 권한 정보를 반환하는 메서드
     * 'ROLE_GUARDIAN' OR 'ROLE_ELDER' 을 반환하여 접근 권한 제어 가능
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * 사용자 이름 반환
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 사용자 비밀번호 반환 (Guardian 은 비밀번호 존재, Elder 은 빈 문자열 반환)
     */
    @Override
    public String getPassword() {
        return password == null ? "" : password;
    }

    /**
     * 계정이 만료되지 않았는지 여부 - 항상 'true'
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겨있지 않은지 여부 - 항상 'true'
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호가 만료되지 않은지 여부 - 항상 'true'
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 활성화되어 있는지 여부 - 항상 'true'
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
