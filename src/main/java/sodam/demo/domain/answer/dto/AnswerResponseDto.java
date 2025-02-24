package sodam.demo.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sodam.demo.domain.question.entity.Question;

import java.time.LocalDate;
import java.util.List;

public class AnswerResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerDateDto{
        private LocalDate recordDate;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerDateListDto{
        private List<AnswerDateDto> answerDateDtoList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerDetailDto{
        private Long questionId;
        private String questionText;
        private String answerText;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerDetailListDto{
        List<AnswerDetailDto> answerDetailDtoList;
    }
}
