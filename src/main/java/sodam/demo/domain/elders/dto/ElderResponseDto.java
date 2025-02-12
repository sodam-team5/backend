package sodam.demo.domain.elders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ElderResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ElderHomeResponseDto{
        private Long elderId;
        private String name;
    }
}
