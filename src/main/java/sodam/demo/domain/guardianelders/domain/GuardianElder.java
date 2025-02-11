package sodam.demo.domain.guardianelders.domain;

import jakarta.persistence.*;
import sodam.demo.domain.elders.domain.Elder;
import sodam.demo.domain.guardians.domain.Guardian;
import sodam.demo.global.common.BaseEntity;

@Entity
public class GuardianElder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guardian_id")
    private Guardian guardian;

    @ManyToOne
    @JoinColumn(name = "elder_id")
    private Elder elder;
}
