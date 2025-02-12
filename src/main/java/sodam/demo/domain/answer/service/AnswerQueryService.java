package sodam.demo.domain.answer.service;

import sodam.demo.domain.answer.dto.AnswerResponseDto;

import java.time.LocalDate;

public interface AnswerQueryService {
    AnswerResponseDto.AnswerDateListDto getElderAnswerDateList(Long elderId);
    AnswerResponseDto.AnswerDetailListDto getElderDetailList(Long elderId, LocalDate recordDate);
}
