package sodam.demo.domain.answers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sodam.demo.domain.answers.domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
