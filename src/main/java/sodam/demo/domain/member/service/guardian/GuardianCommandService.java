package sodam.demo.domain.member.service.guardian;

import sodam.demo.domain.member.dto.guardian.GuardianRequestDto;
import sodam.demo.domain.member.dto.guardian.GuardianResponseDto;
import sodam.demo.domain.member.entity.Guardian;

public interface GuardianCommandService {
    Guardian signupGuardian(GuardianRequestDto.GuardianSignupDto request);
    GuardianResponseDto.GuardianLoginResponseDto loginGuardian(String email, String password);
}
