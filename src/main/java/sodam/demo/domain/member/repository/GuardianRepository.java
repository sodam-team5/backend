package sodam.demo.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.member.entity.Guardian;

import java.util.Optional;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
    Optional<Guardian> findByEmail(String email);
    Optional<Guardian> findByName(String name);
}
