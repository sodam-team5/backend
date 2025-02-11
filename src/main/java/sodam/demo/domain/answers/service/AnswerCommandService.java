package sodam.demo.domain.answers.service;

public interface AnswerCommandService {
    void saveAnswer(String transcript, Long questionId, Long elderId);
}
