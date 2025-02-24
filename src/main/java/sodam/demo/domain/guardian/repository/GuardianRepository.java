package sodam.demo.domain.guardian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.guardian.entity.Guardian;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
}
