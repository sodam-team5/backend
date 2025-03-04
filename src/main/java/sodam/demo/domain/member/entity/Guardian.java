package sodam.demo.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import sodam.demo.global.common.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Guardian extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private Role role = Role.GUARDIAN;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public void encodePassword(String encodePassword){
        this.password = encodePassword;
    }

    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    private List<GuardianElder> guardianElders = new ArrayList<>();
}
