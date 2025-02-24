package sodam.demo.domain.answer.converter;

import org.springframework.stereotype.Component;
import sodam.demo.domain.answer.dto.AnswerResponseDto;
import sodam.demo.domain.answer.entity.Answer;
import sodam.demo.domain.elder.entity.Elder;
import sodam.demo.domain.question.entity.Question;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnswerConverter {

    public static Answer toAnswerEntity(String transcript, Long questionId, Long elderId){
        return Answer.builder()
                .answerText(transcript)
                .question(Question.builder().id(questionId).build())
                .elder(Elder.builder().id(elderId).build())
                .build();
    }

    public static AnswerResponseDto.AnswerDateDto toAnswerDateDto(Answer answer){
        return AnswerResponseDto.AnswerDateDto.builder()
                .recordDate(answer.getCreatedAt().toLocalDate())
                .build();
    }

    public static AnswerResponseDto.AnswerDateListDto toAnswerDateListDto(List<Answer> answerList){
        /*
            answerList 가 비어있으면 빈 리스트 반환
         */
        if(answerList == null || answerList.isEmpty()){
            return AnswerResponseDto.AnswerDateListDto.builder()
                    .answerDateDtoList(Collections.emptyList())
                    .build();
        }

        List<AnswerResponseDto.AnswerDateDto> answerDateDtoList = answerList.stream()
                .map(AnswerConverter::toAnswerDateDto)
                .map(AnswerResponseDto.AnswerDateDto::getRecordDate)
                .distinct()
                .map(recordDate -> new AnswerResponseDto.AnswerDateDto(recordDate))
                .collect(Collectors.toList());

        return AnswerResponseDto.AnswerDateListDto.builder()
                .answerDateDtoList(answerDateDtoList)
                .build();
    }

    public static AnswerResponseDto.AnswerDetailDto toAnswerDetailDto(Answer answer){
        return AnswerResponseDto.AnswerDetailDto.builder()
                .questionId(answer.getQuestion().getId())
                .questionText(answer.getQuestion().getQuestionText())
                .answerText(answer.getAnswerText())
                .build();
    }

    public static AnswerResponseDto.AnswerDetailListDto toAnswerDetailListDto(List<Answer> answerList){
        if(answerList == null || answerList.isEmpty()){
            return AnswerResponseDto.AnswerDetailListDto.builder()
                    .answerDetailDtoList(Collections.emptyList())
                    .build();
        }

        List<AnswerResponseDto.AnswerDetailDto> answerDetailDtoList = answerList.stream()
                .map(AnswerConverter::toAnswerDetailDto)
                .collect(Collectors.toList());

        return AnswerResponseDto.AnswerDetailListDto.builder()
                .answerDetailDtoList(answerDetailDtoList)
                .build();
    }
}
