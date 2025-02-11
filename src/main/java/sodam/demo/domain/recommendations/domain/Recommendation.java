package sodam.demo.domain.recommendations.domain;

import jakarta.persistence.*;
import sodam.demo.domain.answers.domain.Answer;
import sodam.demo.domain.questions.domain.Question;
import sodam.demo.global.common.BaseEntity;

@Entity
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
