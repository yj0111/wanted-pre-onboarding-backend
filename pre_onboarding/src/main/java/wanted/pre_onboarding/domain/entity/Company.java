package wanted.pre_onboarding.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false, length = 100)
    private String companyName; // 회사명

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Posting> postings;
}
