package sodam.demo.domain.guardians.domain;

import jakarta.persistence.*;
import sodam.demo.domain.guardianelders.domain.GuardianElder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    private List<GuardianElder> guardianElders = new ArrayList<>();
}
