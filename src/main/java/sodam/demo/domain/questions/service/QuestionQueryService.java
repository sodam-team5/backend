package sodam.demo.domain.questions.service;

import sodam.demo.domain.questions.dto.QuestionResponseDto;

public interface QuestionQueryService {
    QuestionResponseDto.QuestionDto getQuestion(Long questionId);
    QuestionResponseDto.QuestionDto getNextQuestion(Long questionId);
}
