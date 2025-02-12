package sodam.demo.domain.interest.entity;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.elder.entity.ElderInterest;
import sodam.demo.domain.question.entity.Question;
import sodam.demo.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "interest", cascade = CascadeType.ALL)
    private List<ElderInterest> elderInterests = new ArrayList<>();

    @OneToMany(mappedBy = "interest", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();
}
