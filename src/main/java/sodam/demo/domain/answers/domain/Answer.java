package sodam.demo.domain.answers.domain;

import jakarta.persistence.*;
import sodam.demo.domain.elders.domain.Elder;
import sodam.demo.domain.questions.domain.Question;
import sodam.demo.domain.recommendations.domain.Recommendation;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Answer {
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
