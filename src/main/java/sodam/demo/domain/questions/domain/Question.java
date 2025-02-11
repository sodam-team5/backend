package sodam.demo.domain.questions.domain;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.answers.domain.Answer;
import sodam.demo.domain.interests.domain.Interest;
import sodam.demo.domain.recommendations.domain.Recommendation;
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
