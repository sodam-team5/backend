package sodam.demo.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.interest.entity.Interest;
import sodam.demo.global.common.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
