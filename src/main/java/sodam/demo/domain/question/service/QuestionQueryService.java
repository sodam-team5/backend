package sodam.demo.domain.question.service;

import sodam.demo.domain.question.dto.QuestionResponseDto;

public interface QuestionQueryService {
    QuestionResponseDto.QuestionDto getQuestion(Long questionId);
    QuestionResponseDto.QuestionDto getNextQuestion(Long questionId);
}
