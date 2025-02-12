package sodam.demo.domain.questions.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.questions.converter.QuestionConverter;
import sodam.demo.domain.questions.domain.Question;
import sodam.demo.domain.questions.dto.QuestionResponseDto;
import sodam.demo.domain.questions.repository.QuestionRepository;
import sodam.demo.global.apipayload.status.ErrorStatus;
import sodam.demo.global.exception.QuestionException;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionQueryServiceImpl implements QuestionQueryService{
    private final QuestionRepository questionRepository;

    @Override
    public QuestionResponseDto.QuestionDto getQuestion(Long questionId){
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()-> new QuestionException(ErrorStatus.QUESTION_NOT_FOUND));
        return QuestionConverter.toQuestionDto(question);
    }

    @Override
    public QuestionResponseDto.QuestionDto getNextQuestion(Long questionId){
        Question nextQuestion = questionRepository.findById(questionId + 1)
                .orElseThrow(()-> new QuestionException(ErrorStatus.QUESTION_NOT_FOUND));
        return QuestionConverter.toQuestionDto(nextQuestion);
    }
}
