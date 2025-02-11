package sodam.demo.domain.questions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.questions.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
