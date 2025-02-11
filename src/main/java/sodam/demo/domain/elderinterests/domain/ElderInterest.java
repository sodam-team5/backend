package sodam.demo.domain.elderinterests.domain;

import jakarta.persistence.*;
import sodam.demo.domain.elders.domain.Elder;
import sodam.demo.domain.interests.domain.Interest;
import sodam.demo.global.common.BaseEntity;

@Entity
public class ElderInterest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "elder_id")
    private Elder elder;

    @ManyToOne
    @JoinColumn(name = "interest_id")
    private Interest interest;
}
