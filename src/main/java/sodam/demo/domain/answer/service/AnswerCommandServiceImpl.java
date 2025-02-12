package sodam.demo.domain.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.answer.converter.AnswerConverter;
import sodam.demo.domain.answer.entity.Answer;
import sodam.demo.domain.answer.repository.AnswerRepository;

@Service
@RequiredArgsConstructor
public class AnswerCommandServiceImpl implements AnswerCommandService{
    private final AnswerRepository answerRepository;
    private final AnswerConverter answerConverter;

    @Override
    public void saveAnswer(String transcript, Long questionId, Long elderId){
        Answer answer = answerConverter.toAnswerEntity(transcript, questionId, elderId);
        answerRepository.save(answer);
    }
}
