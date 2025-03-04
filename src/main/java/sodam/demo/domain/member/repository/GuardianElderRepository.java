package sodam.demo.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.member.entity.Elder;
import sodam.demo.domain.member.entity.Guardian;
import sodam.demo.domain.member.entity.GuardianElder;

import java.util.List;

public interface GuardianElderRepository extends JpaRepository<GuardianElder, Long> {
    List<GuardianElder> findByGuardianId(Long guardianId);
    boolean existsByElderAndGuardian(Elder elder, Guardian guardian);
}
