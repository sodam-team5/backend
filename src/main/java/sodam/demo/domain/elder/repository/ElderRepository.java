package sodam.demo.domain.elder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sodam.demo.domain.elder.entity.Elder;

public interface ElderRepository extends JpaRepository<Elder, Long> {
}
