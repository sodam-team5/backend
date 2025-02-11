package sodam.demo.domain.answers.converter;

import org.springframework.stereotype.Component;
import sodam.demo.domain.answers.domain.Answer;
import sodam.demo.domain.elders.domain.Elder;
import sodam.demo.domain.questions.domain.Question;

@Component
public class AnswerConverter {

    /*
        텍스트 데이터를 Answer 엔티티로 변환하는 역할 (DTO -> 엔티티 변환)
     */
    public Answer toAnswerEntity(String transcript, Long questionId, Long elderId){
        return Answer.builder()
                .answerText(transcript)
                .question(Question.builder().id(questionId).build())
                .elder(Elder.builder().id(elderId).build())
                .build();
    }
}
