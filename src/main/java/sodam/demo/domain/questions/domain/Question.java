package sodam.demo.domain.questions.domain;

import jakarta.persistence.*;
import sodam.demo.domain.answers.domain.Answer;
import sodam.demo.domain.interests.domain.Interest;
import sodam.demo.domain.recommendations.domain.Recommendation;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
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
