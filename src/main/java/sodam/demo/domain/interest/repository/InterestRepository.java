package sodam.demo.domain.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.interest.entity.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
