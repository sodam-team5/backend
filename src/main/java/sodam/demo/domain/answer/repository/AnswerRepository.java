package sodam.demo.domain.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sodam.demo.domain.answer.entity.Answer;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByElderId(Long elderId);

    @Query("SELECT a FROM Answer a WHERE a.elder.id = :elderId AND DATE(a.createdAt) = :recordDate")
    List<Answer> findByElderIdAndCreatedAtDate(@Param("elderId") Long elderId,
                                               @Param("recordDate") LocalDate recordDate);
}
