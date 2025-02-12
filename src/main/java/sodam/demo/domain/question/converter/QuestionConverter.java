package sodam.demo.domain.question.converter;

import sodam.demo.domain.question.entity.Question;
import sodam.demo.domain.question.dto.QuestionResponseDto;

public class QuestionConverter {

    public static QuestionResponseDto.QuestionDto toQuestionDto(Question question){
        return QuestionResponseDto.QuestionDto.builder()
                .questionId(question.getId())
                .question(question.getQuestionText())
                .build();
    }
}
