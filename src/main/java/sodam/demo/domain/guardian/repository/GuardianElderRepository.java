package sodam.demo.domain.guardian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.guardian.entity.GuardianElder;

import java.util.List;

public interface GuardianElderRepository extends JpaRepository<GuardianElder, Long> {
    List<GuardianElder> findByGuardianId(Long guardianId);
}
