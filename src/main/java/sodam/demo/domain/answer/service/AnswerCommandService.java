package sodam.demo.domain.answer.service;

public interface AnswerCommandService {
    void saveAnswer(String transcript, Long questionId, Long elderId);
}
