package sodam.demo.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
