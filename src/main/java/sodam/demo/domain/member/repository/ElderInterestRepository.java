package sodam.demo.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.member.entity.ElderInterest;

public interface ElderInterestRepository extends JpaRepository<ElderInterest, Long> {
}
