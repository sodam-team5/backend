package sodam.demo.domain.answers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.answers.converter.AnswerConverter;
import sodam.demo.domain.answers.domain.Answer;
import sodam.demo.domain.answers.repository.AnswerRepository;

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
