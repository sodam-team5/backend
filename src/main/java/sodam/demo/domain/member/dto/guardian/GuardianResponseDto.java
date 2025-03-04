package sodam.demo.domain.member.dto.guardian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class GuardianResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GuardianSignupResponseDto {
        private Long guardianId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GuardianLoginResponseDto {
        private Long guardianId;
        private String accessToken;
        private String refreshToken;
    }
}
