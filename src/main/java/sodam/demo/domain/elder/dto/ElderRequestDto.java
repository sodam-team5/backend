package sodam.demo.domain.elder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ElderRequestDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ElderLoginDto{
        private String elderName;
        private String guardianName;
    }
}
