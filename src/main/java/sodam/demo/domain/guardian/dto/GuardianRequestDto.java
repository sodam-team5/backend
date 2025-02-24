package sodam.demo.domain.guardian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class GuardianRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GuardianSignupDto{
        private String name;
        private String email;
        private String password;
        private LocalDate birthDate;
    }
}
