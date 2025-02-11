package sodam.demo.domain.elders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.elders.domain.Elder;

public interface ElderRepository extends JpaRepository<Elder, Long> {
}
