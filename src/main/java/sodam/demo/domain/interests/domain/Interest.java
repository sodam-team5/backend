package sodam.demo.domain.interests.domain;

import jakarta.persistence.*;
import sodam.demo.domain.elderinterests.domain.ElderInterest;
import sodam.demo.domain.questions.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Interest {
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
