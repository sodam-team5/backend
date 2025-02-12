package sodam.demo.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionDto {
        private Long questionId;
        private String question;
    }
}
