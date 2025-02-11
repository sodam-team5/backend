package sodam.demo.domain.elders.domain;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.domain.answers.domain.Answer;
import sodam.demo.domain.elderinterests.domain.ElderInterest;
import sodam.demo.domain.guardianelders.domain.GuardianElder;
import sodam.demo.global.common.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Elder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL)
    private List<GuardianElder> guardianElders = new ArrayList<>();;

    @OneToMany(mappedBy = "elder",  cascade = CascadeType.ALL)
    private List<ElderInterest> elderInterests = new ArrayList<>();

    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();
}
