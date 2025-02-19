package sodam.demo.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.answer.converter.AnswerConverter;
import sodam.demo.domain.answer.dto.AnswerResponseDto;
import sodam.demo.domain.answer.entity.Answer;
import sodam.demo.domain.answer.repository.AnswerRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerQueryServiceImpl implements AnswerQueryService{
    private final AnswerRepository answerRepository;

    @Override
    public AnswerResponseDto.AnswerDateListDto getElderAnswerDateList(Long elderId){
        List<Answer> answerList = answerRepository.findByElderId(elderId);
        return AnswerConverter.toAnswerDateListDto(answerList);
    }

    @Override
    public AnswerResponseDto.AnswerDetailListDto getElderDetailList(Long elderId, LocalDate recordDate){
        List<Answer> answerList = answerRepository.findByElderId(elderId);
        return AnswerConverter.toAnswerDetailListDto(answerList);
    }
}
