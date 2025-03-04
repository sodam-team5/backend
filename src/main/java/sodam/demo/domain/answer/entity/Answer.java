package sodam.demo.domain.answer.entity;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.member.entity.Elder;
import sodam.demo.domain.question.entity.Question;
import sodam.demo.domain.recommendation.entity.Recommendation;
import sodam.demo.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String answerText;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String summaryText;

    @ManyToOne
    @JoinColumn(name = "elder_id")
    private Elder elder;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<Recommendation> recommendations = new ArrayList<>();
}
