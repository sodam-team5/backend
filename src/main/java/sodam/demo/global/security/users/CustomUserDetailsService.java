package sodam.demo.global.security.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sodam.demo.domain.member.entity.Elder;
import sodam.demo.domain.member.entity.Role;
import sodam.demo.domain.member.repository.ElderRepository;
import sodam.demo.domain.member.entity.Guardian;
import sodam.demo.domain.member.repository.GuardianElderRepository;
import sodam.demo.domain.member.repository.GuardianRepository;

import java.util.Optional;

/**
 * Spring Security 에서 사용자 정보를 데이터베이스에서 조회하는 서비스
 * 로그인 시에 CustomUserDetails 객체를 생성하여 반환
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final GuardianRepository guardianRepository;
    private final ElderRepository elderRepository;
    private final GuardianElderRepository guardianElderRepository;

    /**
     * 사용자 ID 로 사용자 정보를 조회하는 메서드
     * @param identifier 사용자 인증 식별자 (Guardian : email, Elder : elderName:guardianName)
     * @return 'CustomUserDetails' 객체
     * @throws UsernameNotFoundException 사용자가 존재하지 않을 경우 예외 발생
     */
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException{

        // 보호자 로그인
        Optional<Guardian> guardian = guardianRepository.findByEmail(identifier);
        if(guardian.isPresent()){
            return new CustomUserDetails(
                    guardian.get().getMemberId(),
                    guardian.get().getEmail(),
                    guardian.get().getPassword(),
                    Role.GUARDIAN
            );
        }

        // 노인 로그인
        String[] parts = identifier.split(":");
        if(parts.length != 2){
            String elderName = parts[0];
            String guardianName = parts[1];

            Optional<Elder> elder = elderRepository.findByName(elderName);
            Optional<Guardian> guardianOpt = guardianRepository.findByName(guardianName);
            if(elder.isEmpty() || guardianOpt.isEmpty()){
                throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
            }

            boolean isLinked = guardianElderRepository.existsByElderAndGuardian(elder.get(), guardianOpt.get());
            if (!isLinked) {
                throw new UsernameNotFoundException("해당 보호자와 노인의 관계가 존재하지 않습니다.");
            }

            return new CustomUserDetails(
                    elder.get().getMemberId(),
                    identifier,
                    "",
                    Role.ELDER
            );
        }
        throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다.");
    }
}
