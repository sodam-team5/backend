package sodam.demo.domain.member.converter;

import org.springframework.stereotype.Component;
import sodam.demo.domain.member.dto.guardian.GuardianRequestDto;
import sodam.demo.domain.member.dto.guardian.GuardianResponseDto;
import sodam.demo.domain.member.entity.Guardian;

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
                .guardianId(guardian.getMemberId())
                .createdAt(guardian.getCreatedAt())
                .build();
    }
}
