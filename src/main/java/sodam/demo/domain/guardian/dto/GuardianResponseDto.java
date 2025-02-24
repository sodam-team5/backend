package sodam.demo.domain.guardian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
}
