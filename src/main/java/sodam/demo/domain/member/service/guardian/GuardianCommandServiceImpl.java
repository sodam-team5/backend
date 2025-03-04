package sodam.demo.domain.member.service.guardian;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sodam.demo.domain.member.converter.GuardianConverter;
import sodam.demo.domain.member.dto.guardian.GuardianRequestDto;
import sodam.demo.domain.member.dto.guardian.GuardianResponseDto;
import sodam.demo.domain.member.entity.Guardian;
import sodam.demo.domain.member.entity.Role;
import sodam.demo.domain.member.repository.GuardianRepository;
import sodam.demo.global.security.jwt.JwtTokenProvider;
import sodam.demo.global.security.users.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class GuardianCommandServiceImpl implements GuardianCommandService {
    private final GuardianRepository guardianRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public Guardian signupGuardian(GuardianRequestDto.GuardianSignupDto request){
        Guardian newGuardian = GuardianConverter.toGuardian(request);
        newGuardian.encodePassword(passwordEncoder.encode(request.getPassword()));
        newGuardian = guardianRepository.save(newGuardian);
        return newGuardian;
    }

    @Override
    @Transactional
    public GuardianResponseDto.GuardianLoginResponseDto loginGuardian(String email, String password){
        // AuthenticationManager 을 사용하여 인증 수행
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        // 인증된 사용자 정보 조회
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Long memberId = userDetails.getMemberId();
        Role role = userDetails.getRole();

        String accessToken = jwtTokenProvider.generateAccessToken(memberId, role);
        String refreshToken = jwtTokenProvider.generateRefreshToken(memberId, role);

        return GuardianResponseDto.GuardianLoginResponseDto.builder()
                .guardianId(memberId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
