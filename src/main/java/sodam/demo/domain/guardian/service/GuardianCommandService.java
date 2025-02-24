package sodam.demo.domain.guardian.service;

import sodam.demo.domain.guardian.dto.GuardianRequestDto;
import sodam.demo.domain.guardian.entity.Guardian;

public interface GuardianCommandService {
    Guardian signupGuardian(GuardianRequestDto.GuardianSignupDto request);
}
