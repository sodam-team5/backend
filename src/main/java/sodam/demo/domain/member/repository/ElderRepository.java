package sodam.demo.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.member.entity.Elder;

import java.util.Optional;

public interface ElderRepository extends JpaRepository<Elder, Long> {
    Optional<Elder> findByName(String name);
}
