package sodam.demo.domain.guardian.entity;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.elder.entity.Elder;
import sodam.demo.global.common.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
