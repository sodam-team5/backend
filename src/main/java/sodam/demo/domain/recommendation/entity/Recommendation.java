package sodam.demo.domain.recommendation.entity;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.answer.entity.Answer;
import sodam.demo.domain.question.entity.Question;
import sodam.demo.global.common.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recommendation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String recommendationText;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
