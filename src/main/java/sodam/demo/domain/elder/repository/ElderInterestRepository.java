package sodam.demo.domain.elder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.elder.entity.ElderInterest;

public interface ElderInterestRepository extends JpaRepository<ElderInterest, Long> {
}
