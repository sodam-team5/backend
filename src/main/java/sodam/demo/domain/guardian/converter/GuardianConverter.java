package sodam.demo.domain.guardian.converter;

import org.springframework.stereotype.Component;
import sodam.demo.domain.guardian.dto.GuardianRequestDto;
import sodam.demo.domain.guardian.dto.GuardianResponseDto;
import sodam.demo.domain.guardian.entity.Guardian;

@Component
public class GuardianConverter {

    public static Guardian toGuardian(GuardianRequestDto.GuardianSignupDto request){
        return Guardian.builder()
                .name(request.getName())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .password(request.getPassword())
                .build();
    }

    public static GuardianResponseDto.GuardianSignupResponseDto toGuardianSignupResultDto(Guardian guardian){
        return GuardianResponseDto.GuardianSignupResponseDto.builder()
                .guardianId(guardian.getId())
                .createdAt(guardian.getCreatedAt())
                .build();
    }
}
