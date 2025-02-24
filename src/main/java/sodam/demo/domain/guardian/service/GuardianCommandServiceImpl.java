package sodam.demo.domain.guardian.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sodam.demo.domain.guardian.converter.GuardianConverter;
import sodam.demo.domain.guardian.dto.GuardianRequestDto;
import sodam.demo.domain.guardian.entity.Guardian;
import sodam.demo.domain.guardian.repository.GuardianRepository;

@Service
@RequiredArgsConstructor
public class GuardianCommandServiceImpl implements GuardianCommandService{
    private final GuardianRepository guardianRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Guardian signupGuardian(GuardianRequestDto.GuardianSignupDto request){
        Guardian newGuardian = GuardianConverter.toGuardian(request);
        newGuardian.encodePassword(passwordEncoder.encode(request.getPassword()));
        newGuardian = guardianRepository.save(newGuardian);
        return newGuardian;
    }
}
