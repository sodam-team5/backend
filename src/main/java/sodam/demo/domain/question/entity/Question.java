package sodam.demo.domain.question.entity;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.interest.entity.Interest;
import sodam.demo.domain.recommendation.entity.Recommendation;
import sodam.demo.domain.answer.entity.Answer;
import sodam.demo.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "interest_id")
    private Interest interest;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations = new ArrayList<>();
}
