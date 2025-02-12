package sodam.demo.domain.questions.converter;

import sodam.demo.domain.questions.domain.Question;
import sodam.demo.domain.questions.dto.QuestionResponseDto;

public class QuestionConverter {

    public static QuestionResponseDto.QuestionDto toQuestionDto(Question question){
        return QuestionResponseDto.QuestionDto.builder()
                .questionId(question.getId())
                .question(question.getQuestionText())
                .build();
    }
}
